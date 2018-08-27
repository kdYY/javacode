package suanfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class merge {

    public static int[] merge(int[] nums1,int[] nums2){

        int i=nums1.length-1;
        int j=nums2.length-1;

        int[] arr = new int[i+j+2];

        for(int k=0;k<nums1.length;k++){
            arr[k] = nums1[k];
        }

        //牛逼的代码
        while(i>=0&&j>=0){
            if(arr[i]>nums2[j]){
                arr[i+j+1]=arr[i];
                i--;
            }else{
                arr[i+j+1]=nums2[j];
                j--;
            }
        }
        while(j>=0){
            arr[i+j+1]=nums2[j];
            j--;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] a = {1,3,6,9,12,15}/**/;
        int[] b = {2,4,7,8,10,16,18,22,24};
//      int [] arr = merge(a,b);
        for (int i:
                merge(b,a)) {
            System.out.printf("%d ",i);
        }
        System.out.println();




    }



}
