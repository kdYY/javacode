package SimpleMR2.test;

import java.util.concurrent.*;

public  class testSemaphore2 {
    final private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(20);
        ExecutorService exec = new ThreadPoolExecutor(20,200,20,TimeUnit.SECONDS,queue);
        final Semaphore semaphore = new Semaphore(3);//3个并发
        for(int i=0;i<threadCount;i++){                       //200个请求
            final int threadNum = i;
            exec.execute(()->{
                try {
                    if(semaphore.tryAcquire(1,500,TimeUnit.MILLISECONDS)){//尝试获取一个许可,拿不到直接返回错误
                        test(threadNum);
                        semaphore.release(1);//释放一个许可
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //    exec.shutdownNow();
       exec.shutdown();//等待线程池中的线程执行完毕之后进行关闭操作
        Thread.sleep(2000);
    }

    public static void test(int num) throws InterruptedException {
        Thread.sleep(1501);
        System.out.println(num + Thread.currentThread().getName());
    }



}
