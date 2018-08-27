package BigintAndUUid;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.atomic.AtomicInteger;

public class test1 {
    public DruidManager druidManager = DruidManager.getInstance();
    public static long idd = 1000000L;

    public  void test() {
        Connection con = null;
        PreparedStatement ps = null;
        AtomicInteger errori =  new AtomicInteger(0);
        AtomicInteger i =  new AtomicInteger(0);
        String sqllanguages = "insert into test_bigintAsString_t(id,name,age,loves,cardnum) values(?,?,?,?,?)";

        //ResultSet rs = null;
        try {
            con = druidManager.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sqllanguages);
            while(i.get() < 100) {
                for (int id = 0; id < 1000; id++) {
                    idd++;
                    ps.setString(1, "1000000000"+idd);
                    ps.setString(2, "student" + id);
                    ps.setInt(3, 20);
                    ps.setString(4, "BigintAndUUid.test");
                    ps.setString(5, "cardum");
                    ps.addBatch();
                }

                long starttime = System.currentTimeMillis();
                ps.executeBatch();
                con.commit();
                long endtime = System.currentTimeMillis();
                System.out.println("========================================");
                System.out.println(Thread.currentThread().getName()+"add 1000个记录第"+i.incrementAndGet()+"次");
                System.out.println("此次花费时间：" + (endtime-starttime)/1000 + "秒！！");
                System.out.println("---------------------------------------");
               // i.incrementAndGet();
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("failed"+errori.incrementAndGet());
        }finally {
            try{
                ps.close();
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"线程完成");
        }
    }

    @Test
    public synchronized void test3(){
        test t = new test();
        t.test();

    }

    public static void main(String[] args) throws Exception{
        long starttime = System.currentTimeMillis();
        test1 t = new test1();
        /*ExecutorService exes = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int threadNum = 0 ;threadNum<10;threadNum++){
            exes.submit(new Runnable() {
                @Override
                public void run() {
                    t.BigintAndUUid.test();
                }
            });
        }

        exes.shutdown();
        exes.awaitTermination(1, TimeUnit.HOURS);*/
        t.test();
        long endtime = System.currentTimeMillis();
        double a = (endtime-starttime)/1000;
        System.out.println("程序花费时间：" + a + "秒！！");


    }


}
