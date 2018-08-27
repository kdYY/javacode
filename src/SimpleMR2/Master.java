package SimpleMR2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public  class Master<E> {

    //存放E类型任务的容器，只对taskQuueu进行写
    private volatile BlockingQueue<? super E> TaskQueue;
    //存放每一个worker任务结果的集合，只对result进行读
    private volatile BlockingQueue<? super Object> resultQueue;
    //存放worker的合集
    private volatile Map<String,Callable> wokersMap;
    //执行任务的线程池
    private  ThreadPoolExecutor threadPool;
    //存放运行结果
    //该Master运行完成次数
    //TODO
    private AtomicInteger finishNum = new AtomicInteger(-1);
    //初始化capacity
    private static Integer DEFAULT_CAPACITY = 12000;
    //使用锁
    private volatile Lock lock = new ReentrantLock();
    //任务的future队列
    private List<Future> futureList = new ArrayList<Future>();
    //整个master标志
    private AtomicBoolean flag = new AtomicBoolean(false);
    //日志
    private final static Logger logger = LoggerFactory.getLogger(Master.class);


    /**
     * 构造一个大小为30，任务容量为capacity的线程池，初始化workQueue和workerMap
     * @param worker
     * @param workThreads
     * @param capacity
     */
    public Master(Worker worker, Integer workThreads, Integer capacity){
        threadPool = new ThreadPoolExecutor(
                10,
                30,
                1000L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(capacity),
                new MWRejection());

        TaskQueue = new ArrayBlockingQueue<E>(capacity);
        wokersMap = new HashMap<String,Callable>();
        resultQueue = new LinkedBlockingQueue<Object>();

        worker.setTaskQueue(this.TaskQueue);
        worker.setResultQueue(this.resultQueue);

        for (Integer i = 0; i < workThreads; i++) {
            wokersMap.put(Integer.toString(i),worker);
        }
        logger.info("{}个worker已经启动",workThreads);

    }

    //构造
    public Master(Worker worker, Integer workThreads){
        this(worker,workThreads,DEFAULT_CAPACITY);
    }

    //执行任务
    public void executeThread(){
        wokersMap.forEach((k,v)->{
            Future future = threadPool.submit(v);
            futureList.add(future);
        });
    }

    //提交任务
    public void submitTask(E task){
        lock.lock();
        try {
            this.TaskQueue.put(task);
            logger.info("task add {}",task.toString());
        }catch (Exception e){
            System.out.println("提交失败");
            logger.error("{}提交失败",task);
        }finally {
            lock.unlock();
        }
    }

    //过程中间判断，非阻塞
    public boolean isComplete(){
        lock.lock();
        try{
            if(futureList != null)
                for (Future f : futureList) {
                    if (!f.isDone()) {
                        System.out.println("任务尚未做完");
                        return false;
                    }
                }
            return true;
        }catch (Exception e){
            return flag.get();
        }finally {
            System.out.println("准备释放lock");
            lock.unlock();
        }
    }

    //最终状态判断，阻塞
    public  Boolean  get(){
        lock.lock();
        try {
            System.out.println("尝试拿锁");
//            lock.tryLock(10,TimeUnit.SECONDS);
            flag.set(true);
            for (Future f : futureList) {
                if(!(Boolean) f.get()){
                    flag.set(false);
                    System.out.println("future failure");
                }
            }
        } catch (Exception e)  {
            flag.set(false);
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        threadPool.shutdownNow();
        System.out.println("完成了"+flag.get());
        return flag.get();
    }


    /*public Integer getResultIfNum(Class<E> clazz){
        if(isComplete()){
            //汇总
            getSum(clazz);
        }
        //任务尚未结束
        return finishNum.get();
    }*/

    //计算结果方法

    /**
     * 将Worker类型传入
     * @param
     * @return
     */
    public Integer getFinishedSum(){
        lock.lock();
         Integer sum = 0;
        try{
            if (this.get()){
                if( resultQueue != null/* && threadPool.isTerminated()*/){
                    this.finishNum.incrementAndGet();
                    for (Object v:
                         resultQueue) {
                        if(v != null){
//                            this.finishNum.incrementAndGet();
                            sum += (Integer) v;
                        }
                    }
//                    System.out.println(resultQueue.size());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return sum;

    }

    //汇总要做的事





}
