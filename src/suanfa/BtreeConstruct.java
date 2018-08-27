package suanfa;

import java.util.Arrays;
import java.util.Scanner;

public class BtreeConstruct {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] scanFBtree = new int[8];
        int[] scanCBtree = new int[8];
        while(scan.hasNextInt()){
            for(int i=0;i<scanFBtree.length;i++){
                scanFBtree[i] = scan.nextInt();
            }
            for(int i=0;i<scanCBtree.length;i++){
                scanCBtree[i] = scan.nextInt();
            }
            System.out.println(Arrays.toString(scanFBtree));
            System.out.println(Arrays.toString(scanCBtree));
            break;
        }
    }

    public TreeNode ReConstructBTree(int[] fb,int[] cb,int fStart, int fEnd,int cstart,int cend){
        TreeNode root = new TreeNode(fb[fStart]);
        if(fb.length == 1 || (fStart == fEnd && cstart == cend)){
            root.left = null;
            root.right = null;
            return root;
        }
        int rootVal = root.root;
        int partition =  0;
        for(int i = cstart;i<cend;i++){
            if(cb[i] == rootVal){
                partition = i;
                break;
            }
        }

        int leftLength = partition - cstart;
        int rightLength =  cend - partition;

        if(leftLength > 0){
            root.left = ReConstructBTree(fb,cb, fStart+1, fStart+leftLength, cstart,leftLength-1);
        }

        if(rightLength > 0){
            root.right = ReConstructBTree(fb,cb, fStart+leftLength+1, fEnd, partition+1,cend);
        }

        return root;
    }
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int root;

        TreeNode(int root){
            this.root = root;
            left = null;
            right = null;

        }
    }

}
