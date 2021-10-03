package com.atguigu.tree;

import java.util.Arrays;

/**
 * @author 顾遥
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, -1, 3, 5, 1, 2};

        System.out.println(Arrays.toString(arr));

        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 堆排序
     *
     * @param arr 排序的数组
     */
    public static void heapSort(int[] arr) {

        int temp;
        // 分步完成
//        adjustHeap(arr, 1, arr.length);
//
//        System.out.println("第一次：" + Arrays.toString(arr));
//        adjustHeap(arr, 0, arr.length);
//
//        System.out.println("第二次：" + Arrays.toString(arr));

        // 构成一个大顶堆或小顶堆,自底向上。
//        进行一般，大顶堆转化
        for (int i = arr.length / PATH - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 将其与末尾元素进行交换，那么此时末尾就是目前的最大值
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // 后面，只需要将堆顶，变为大顶堆就可以了
            adjustHeap(arr, 0, j);
        }


    }

    private static final int PATH = 2;


    /**
     * 将一个数组（二叉树），调整成一个大顶堆
     * <p>
     * 功能：完成 将以 i 对应的非叶子节点的值调整成大顶堆
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 对多少个元素进行调整，length 逐渐减少
     */
    private static void adjustHeap(int[] arr, int i, int length) {
        // 先取出当前元素值，保存在临时变量
        int temp = arr[i];
        //  k = i * 2 + 1 k是 i 节点的 左子节点
        int iLeft = i * 2 + 1;
        // 开始调整
        for (int k = iLeft; k < length; k = k * PATH + 1) {
            // i 的左子节点的值 < 右子节点的值 ,寻找左子节点大，还是右大
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // k 指向右子节点
                k++;
            }
            // 如果子节点 > 父节点
            if (arr[k] > temp) {
                // 把较大值付给当前节点
                arr[i] = arr[k];
                // 将 i 指向 k ，继续循环比较
                i = k;

            } else {
                break;
            }

        }
        // 当 for 循环结束后，我们已经将 i 为父节点的树的最大值，放在最顶部

        // 将 temp 值放到调整的位置
        arr[i] = temp;


    }

}
