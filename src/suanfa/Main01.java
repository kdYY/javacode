package suanfa;

/**
 * n个数 找到最小的k个数
 */
public class Main01 {

    //思路一 : 把整个数组分为k 和 n-k 把n-k个最大的数放在n-k数组里面 时间复杂度O(nk)
    public static void scheme1(int[] ins, int k){
            int[] ks = new int[k];

            for(int i=0; i<k; i++){
                ks[i] = ins[i];
            }

            for(int i=k;i<ins.length;i++){
                for(int j= 0; j<k;j++){
                    if(ks[j] > ins[i]){
                        int temp = ks[j];
                        ks[j] = ins[i];
                        ins[i] = temp;
                    }
                }
            }

        for(int i=0; i<k; i++){
            System.out.println(ks[i]);
        }

    }

    //对获得思路一每次比较k数组中的最大数进行优化，使用最大堆，每次获取堆顶的值比较，成功后再次修改堆结构
    //创建堆
    public static void buildHeap(int[] arr){
//        从最后的一个非叶子节点向上开始排,避免迭代没有意义的叶子节点
        int half = ( arr.length - 1 ) / 2;
        for(int i = half; i > 0; i--){
            maxHeap(arr, arr.length, i);
        }
    }

    private static void maxHeap(int[] arr, int heapSize, int index) {
        //index的左右节点
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        //暂时将最大的数置为arr[index]
        int largest = index;

        //是否存在这样的左右节点,存在且比arr[largest]大即交换
        if(left < heapSize && arr[left] > arr[largest]){
            largest = left;
        }

        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if(index != largest){
            int temp = arr[index];
            arr[index] = arr[largest];
            arr[largest] = temp;
            //不断让最小的值下降到叶子节点
            maxHeap(arr,arr.length,largest);
        }
    }

    public static void scheml2(int[] ins,int k){
        int[] ks = new int[k];

        for(int i=0; i<k; i++){
            ks[i] = ins[i];
        }
        buildHeap(ks);
        for(int i=k;i<ins.length;i++){
            if(ks[0] > ins[i]){
                ks[0] = ins[i];
                maxHeap(ks,ks.length,0);
            }
        }

        for(int i=0; i<k; i++){
            System.out.println(ks[i]);
        }

    }


    public static void main(String[] args) {
        int[] a = {11,2,3,6,9,4,7,8};
        scheml2(a,3);
    }

}
