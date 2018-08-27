package suanfa.jrtt;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 为了不断优化推荐效果，今日头条每天要存储和处理海量数据。假设有这样一种场景：我们对用户按照
 * 它们的注册时间先后来标号，对于一类文章，每个用户都有不同的喜好值，我们会想知道某一段时间内
 * 注册的用户（标号相连的一批用户）中，有多少用户对这类文章喜好值为k。因为一些特殊的原因，不会
 * 出现一个查询的用户区间完全覆盖另一个查询的用户区间(不存在L1<=L2<=R2<=R1)。
 */
public class jrtt01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] scores = new int[10000];
        int[] boolSleep = new int[10000];
        int classMin = 0;
        int keepAlive = 0;
        int i = 0, max = 0;
        //记录唤醒的时刻
        int flag = -1;

        while (in.hasNextInt()) {//注意while处理多个case
            classMin = in.nextInt();
            keepAlive = in.nextInt();
        }
        while (in.hasNextInt()) {//注意while处理多个case
            scores[i] = in.nextInt();
            i++;
        }
        i=0;
        while (in.hasNextInt()) {//注意while处理多个case
            boolSleep[i] = in.nextInt();
            i++;
        }

        for(i=0; i<classMin; i++){
            int temp = 0;
            for(int j=0;j<keepAlive;j++){
                //将keepAlive区间里去掉标识为1的兴趣值，获得唤醒兴趣值
                if(boolSleep[i+j] == 1){
                    continue;
                }
                temp += scores[i+j];
            }
            if(temp > max){
                flag = i;
                max = temp;
            }
        }

        for(i=0; i<classMin; i++){
            if(boolSleep[i] == 1){
                max += scores[i];
            }
        }
        System.out.println(max);
    }

}
