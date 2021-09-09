package com.atguigu.sort;

import java.util.Arrays;

/**
 * @Description
 * @Author 顾遥
 */
public class SimpleSelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1,-1,564,-99,-1,54};
        System.out.println("排序前：" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));


    }

    public static void selectSort(int[] arr) {
        // 总结，使用循环
        int temp;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    // 说明假定最小值不是最小的
                    minIndex = j;
                }

            }
            // 将最小值，和索引0交换
            if (minIndex != i) {
                // 只有不是才交换
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
//                System.out.println("第" + (i+1) + "轮：交换了");

            }
//            System.out.println("第" + (i+1) + "轮：" + Arrays.toString(arr));

        }
        // 第一轮
        // 原数组：101,34,119,1
        // 第一次：1,34,119,101
        // 算法：先简单-->做简单，就是把复杂问题，拆分成简单问题-->逐步解决
        // 第一轮
//        int minIndex = 0;
//        for (int j = minIndex + 1; j < arr.length; j++) {
//            if (arr[minIndex] > arr[j]) {
//                // 说明假定最小值不是最小的
//                minIndex = j;
//            }
//
//        }
//        // 将最小值，和索引0交换
//        int temp;
//        if (minIndex != 0) {
//            temp = arr[0];
//            arr[0] = arr[minIndex];
//            arr[minIndex] = temp;
//            System.out.println("第一轮：" + Arrays.toString(arr));
//        }
//
//        // 第二轮
//        minIndex = 1;
//        for (int j = minIndex + 1; j < arr.length; j++) {
//            if (arr[minIndex] > arr[j]) {
//                // 说明假定最小值不是最小的
//                minIndex = j;
//            }
//
//        }
//        // 将最小值，和索引1交换，如果minIndex动了才交换
//        if (minIndex != 1) {
//            temp = arr[1];
//            arr[1] = arr[minIndex];
//            arr[minIndex] = temp;
//        }
//        System.out.println("第二轮：" + Arrays.toString(arr));
//        // 第三轮
//        minIndex = 2;
//        for (int j = minIndex + 1; j < arr.length; j++) {
//            if (arr[minIndex] > arr[j]) {
//                // 说明假定最小值不是最小的
//                minIndex = j;
//            }
//
//        }
//        // 将最小值，和索引1交换，如果minIndex动了才交换
//        if (minIndex != 2) {
//            temp = arr[2];
//            arr[2] = arr[minIndex];
//            arr[minIndex] = temp;
//        }
//        System.out.println("第三轮：" + Arrays.toString(arr));
    }
}
