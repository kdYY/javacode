package suanfa.jrtt;


import java.util.Scanner;

/*
【编码题】字符串S由小写字母构成，长度为n。定义一种
操作，每次都可以挑选字符串中任意的两个相邻字母进行交换。询问在至多交换m次之后，字符串中最多有多少个连续的位置上的字母相同？*/
public class jrtt02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine() ;

        int num = 2;
        int[][] ap = new  int[str.length()][26];
        int[] m ;//辅助获取每个字母的位置
        int[] b = new int[26];
        int jmax = 0;
        for(int i=0;i<str.length();i++){
            ap[i][str.charAt(i) - 'a'] = 1;
            if(str.charAt(i) - 'a' > jmax){
                jmax = str.charAt(i) - 'a';
            }
        }

        for(int j=0;j<=jmax;j++){
            m = new int[str.length()];
            int k = 0;
            for(int i=0;i<str.length();i++){
                if(ap[i][j] == 1){
                    m[k++] = i;
                }
            }
            if(k == 1)
                b[j] = 1;
            else if (k == 0){
                continue;
            }
            else{
                int temp = -1;
                for(int i=0; i < k; i++){
                    for(int ii=i+1; ii<k; ii++){
                        if(dp(i,ii,m) <= num){
                            if(ii-i+1 > temp)
                                temp = ii-i+1;
                        }
                    }
                }
                b[j] = temp;
            }
        }
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i]+" ");
        }
    }

    private static int dp(int i, int ii, int[] m) {
        if(i == ii){
            return 0;
        }
        if(i == ii-1){
            return m[ii]-m[i]-1;
        }
        return dp(i+1,ii-1,m)+m[ii] - m[i]-ii+i;
    }
}
