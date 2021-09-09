package com.atguigu.sort;

import java.util.Arrays;

/**
 * @Description
 * @Author 顾遥
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 移动法希尔排序
     */
    public static void shellSort2(int[] arr) {
        // 总结
        int temp;
        int j;
//        int c = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在zu进行插入排序
//            c++;
            for (int i = gap; i < arr.length; i++) {

                j = i;
                temp = arr[j];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    // 移动
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                if (j != i) {
                    arr[j] = temp;
                }

            }
//            System.out.println("第" + c + "轮排序后：" + Arrays.toString(arr));

        }
    }

    /**
     * 交换式希尔排序
     */
    public static void shellSort1(int[] arr) {
        // 总结
        int temp;
//        int c = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
//            c++;
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有元素（共gap组，每组10 / gap个元素），步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }

            }
//            System.out.println("第"+c+"轮排序后：" + Arrays.toString(arr));

        }


//        int temp;
//        // 逐步推导
//        // 第一轮
//        // 因为第一轮排序是将10个数据分为10 / 2组
//        for (int i = 5; i < arr.length; i++) {
//            // 遍历各组中所有元素（共五组，每组两个元素），步长5
//            for (int j = i - 5; j >= 0; j -= 5) {
//                // 如果当前元素大于加上步长后的那个元素，说明交换
//                if (arr[j] > arr[j + 5]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//
//        }
//        System.out.println("第一轮排序后：" + Arrays.toString(arr));
//
//        // 第二轮
//        // 因为第二轮排序是将10个数据分为5 / 2 = 2组
//        for (int i = 2; i < arr.length; i++) {
//            // 遍历各组中所有元素（共2组，每组10/2个元素），步长2
//            for (int j = i - 2; j >= 0; j -= 2) {
//                // 如果当前元素大于加上步长后的那个元素，说明交换
//                if (arr[j] > arr[j + 2]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = temp;
//                }
//            }
//
//        }
//        System.out.println("第二轮排序后：" + Arrays.toString(arr));
//        // 第三轮
//        // 因为第二轮排序是将10个数据分为2 / 1 = 1组
//        for (int i = 1; i < arr.length; i++) {
//            // 遍历各组中所有元素（共2组，每组10/1个元素），步长1
//            for (int j = i - 1; j >= 0; j -= 1) {
//                // 如果当前元素大于加上步长后的那个元素，说明交换
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//
//        }
//        System.out.println("第三轮排序后：" + Arrays.toString(arr));

    }
}
