package suanfa;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class quanpailie {

    public static volatile AtomicInteger id = new AtomicInteger(0);
    public static int kk = 0;

    /**
     * 从a这个数组中获取要排列组合的字符，总共要排列出m个长度的字符串存入out中
     * @param a
     * @param m
     * @param out
     */
    public void  combination_cursion(char[] a, int m, char[] out) {
        int i;
        if (m == 0){//out中已经存储了m个数字
           id.incrementAndGet();
           kk++;
           return;
        }
        for (i = a.length; i >= m; i--){//从前向后依次选定一个，动态规划的体现
            out[m - 1] = a[i - 1];//选定一个之后
            combination_cursion(a, m - 1, out);//从前i-1个后选m-1，递归
        }

    }

    @Test
    public void test(){
        String source = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] output = new char[4];
        combination_cursion(source.toCharArray(),4,output);
        System.out.printf("总共%s种",id.get());
    }


}
