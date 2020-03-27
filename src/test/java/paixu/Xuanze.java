package paixu;

/**
 * 选择排序
 */
public class Xuanze {
    public static void main(String[] args) {
        int[] array = {1, 3, 6, 2, 4, 9, 8, 7, 5};

        for(int i=0;i<array.length-1;i++){
           int maxIndex=i;
            for(int j=1;j<array.length-i;j++){
                if(array[maxIndex]>array[j]){
                    maxIndex=j;
                }
            }
            int temp=array[i];
            array[i]=array[maxIndex];
            array[maxIndex]=temp;
        }
        for (int x : array) {
            System.out.print(x);
        }
    }
}
