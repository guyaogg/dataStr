package com.atguigu.sort;

import java.util.Arrays;

/**
 * @Description 归并排序
 * @Author 顾遥
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        mergeSort(arr);
        System.out.println(" 归并排序后：" + Arrays.toString(arr));


    }

    /**
     * 归并算法
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        // 归并排序需要额外空间
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);


    }

    /**
     * 归并排序
     * 分治过程
     *
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 中间索引
            int mid = left + (right - left) / 2;
            // 向左递归
            mergeSort(arr, left, mid, temp);
            // 向右递归
            mergeSort(arr, mid + 1, right, temp);

            // 合并
            mergeSort(arr, left, mid, right, temp);


        }

    }

    /**
     * 归并排序
     * 合并方法
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  作中转数组
     */
    private static void mergeSort(int[] arr, int left, int mid, int right, int[] temp) {
        // 初始化i，    左边有序序列的起始索引
        int i = left;
        // 右边初始索引
        int j = mid + 1;
        // 指向temp的当前索引
        int t = 0;

        //1.
        // 先把左右两边（有序）数据，填充到temp中
        // 直到左右两边有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            // 如果左边有序，小于等于右边有序，就把左边数copy到temp的当前索引
            // 同时copy的元素要向后移1位
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j++];
            }
            t++;
        }

        //2.
        // 把有剩余的一边，依次全部填充到temp
        while (i <= mid) {
            // 说明左边有剩余
            // 全部拷贝过去
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        //3.
        // 将temp数组的数据重新拷贝到原数据
        // 注意，拷贝的范围
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }


    }


}
