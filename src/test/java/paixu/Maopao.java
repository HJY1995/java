package paixu;

/**
 * 冒泡排序
 * 内循环：每轮依次比较相邻两个数的大小，后面比前面小则交换,将大的放在后面
 * 外循环：执行剩下的数字进行比较
 */
public class Maopao {
    public static void main(String[] args) {
        int[] array = {1, 3, 6, 2, 4, 9, 8, 7, 5};
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int x = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = x;
                }
            }
        }
        for (int x : array) {
            System.out.print(x);
        }
    }
}
