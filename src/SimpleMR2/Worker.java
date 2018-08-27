package SimpleMR2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ConcurrentSafe
public abstract class Worker<E> implements Callable {

    private  BlockingQueue<? extends E> taskQueue;//只对taskQueue进行读
    private  BlockingQueue<? super Object> resultQueue;//只对resultQueue进行写

    //使用锁
    private volatile Lock lock = new ReentrantLock();
    private static AtomicLong TaskID = new AtomicLong(0L);

    //发生泛型擦除也没所谓，因为已经在类中显示声明限制类型
    public void setResultQueue(BlockingQueue<Object> resultQueue) {
        this.resultQueue = resultQueue;
    }

    public void setTaskQueue(BlockingQueue<E> taskQueue) {
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
            }
    }

    public Boolean call() {
        while(true){
            try {
                E task = getOne();
                if(task == null){
                    break;
                }
//               synchronized (resultQueue){
                Object result = handle(task);
                resultQueue.put(result);
//               }
                TaskID.incrementAndGet();
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    //处理任务的方法
    public abstract Object handle(E task);

    //获取任务的类型
    public abstract  Class<E> getTaskClass();

    public static long getTaskID(){
        return TaskID.get();
    }

}
