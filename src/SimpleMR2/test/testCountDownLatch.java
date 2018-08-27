package SimpleMR2.test;

import java.util.concurrent.*;

public  class testCountDownLatch {
    final private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(20);
        ExecutorService exec = new ThreadPoolExecutor(20,20,20,TimeUnit.SECONDS,queue);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for(int i=0;i<threadCount;i++){
            final int threadNum = i;
            exec.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(1,TimeUnit.SECONDS);//只等待1秒就执行下面语句
        System.out.println("finish");
       exec.shutdownNow();
        //    exec.shutdown();//等待线程池中的线程执行完毕之后进行关闭操作+
        System.out.println("========================="+countDownLatch.getCount());
        Thread.sleep(2000);
        System.out.println("========================="+countDownLatch.getCount());
    }

    public static void test(int num) throws InterruptedException {
        Thread.sleep(1001);
        System.out.println(num + Thread.currentThread().getName());
    }



}
