package suanfa;

public class jumpFloor {

    public static double jumpFloor(int n){
//        if(n == 1){
//            return 1;
//        }
//        if( n== 2){
//            return 2;
//        }
//         return jumpFloor(n-1) + jumpFloor(n-2);
        double tempFirst = 1;
        double tempSecond = 2;
        while(n > 0){
           if(n == 1 || n == 2){
               break;
           }
            double temp = tempSecond;
            tempSecond = tempFirst+tempSecond;
            tempFirst = temp;
            n--;
        }

        return  tempSecond;
    }

    public static int JumpFloorII(int target) {
        int temp = 0;
        if(target == 1){
            return 1;
        }
        if( target== 2){
            return 2;
        }

        return 2*JumpFloorII(target-1);

    }

    public static void main(String[] args) {
        System.out.println(jumpFloor(3));
    }
}
