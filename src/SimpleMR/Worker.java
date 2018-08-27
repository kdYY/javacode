package SimpleMR;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@UnConcurrentSafe
public abstract class Worker<E> implements Callable {

    private  BlockingQueue<E> taskQueue;//只对taskQueue进行读
    private  ConcurrentHashMap<String,Object> resultMap;//只对resultmap进行写

    //使用锁
    private volatile Lock lock = new ReentrantLock();
    private static AtomicInteger TaskID = new AtomicInteger(0);

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public void setTaskQueue(BlockingQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    private  E getOne(){
        lock.lock();
            try {
                if(taskQueue.size() != 0 && !taskQueue.isEmpty())
                    return this.taskQueue.poll();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }finally {
                lock.unlock();
//                System.out.println(Thread.currentThread().getName()+"获取一个,此时taskqueue容量为："+taskQueue.size());
            }
    }

    public Boolean call() {
        while(true){
            try {
                lock.lock();
                try{
                    E task = getOne();
                    if(task == null){
                        System.out.println("break");
                        break;
                    }
//                System.out.println(task.toString());
//               synchronized (resultMap){

                    Object result = handle(task);
                    this.resultMap.put(Integer.toString(TaskID.get()),result);
                }catch (Exception e){
                    System.out.println("exception");
                }finally {
                    lock.unlock();
                }

//               }

                TaskID.incrementAndGet();

            }catch (Exception e){
                e.printStackTrace();
                return false;
            }finally {
//                lock.unlock();
            }
        }
        System.out.println("-》"+TaskID.get()+"-->"+resultMap.size()+resultMap.get("1000"));
        return true;
    }

    //处理任务的方法
    public abstract Object handle(E task);

    //获取任务的类型
    public abstract  Class<E> getTaskClass();

    public static int getTaskID(){
        return TaskID.get();
    }

}
