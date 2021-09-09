package com.atguigu.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Description
 * @Author 顾遥
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 基数排序 ：空间换时间的经典算法
     * @param arr
     */
    public static void radixSort(int[] arr){
        // 总结
        int[][] bucket = new int[10][arr.length];

        // 为了记录每个桶，实际存放了多少数据，我们定义一个一维数组，记录各个桶放入个数
        int[] bucketElementCounts = new int[bucket.length];
        // 先得到最大数的位数
        // 假设最大数位arr[0]
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]>max){
                max = arr[i];
            }

        }
        // 得到最大数为几位数
        int index = 0;

        int maxLength = String.valueOf(max).intern().length();

        for (int i = 0 ,n = 1; i < maxLength; i++,n *= 10) {
            for (int j = 0; j < arr.length;j++){
                // 取出每个元素的i位
                int digitOfElement = arr[j] / n % 10;
                // 放入到对应桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement] += 1;
            }
            // 根据桶顺序，放回数组
            index = 0;
            // 遍历每个桶，并将桶中数据放入数组
            for (int k = 0; k < bucket.length; k++) {
                // 如果桶中有数据，我们才放入原数组
                for(int l = 0; l < bucketElementCounts[k];l++){
                    // 取出元素到 原数组
                    arr[index] = bucket[k][l];
                    index++;
                }
                // bucketElementCounts元素置零
                bucketElementCounts[k] = 0;

            }
        }


        // 使用循环将代码处理


        // 第一轮排序(针对个位
        // 定义一个二维数组,表示十个桶
        // 说明桶的大小,防止溢出，大小位数组的大小,空间要求比较高
//        int[][] bucket = new int[10][arr.length];
//
//        // 为了记录每个桶，实际存放了多少数据，我们定义一个一维数组，记录各个桶放入个数
//        int[] bucketElementCounts = new int[bucket.length];
//
//        for (int j = 0; j < arr.length;j++){
//            // 取出每个元素的个位
//            int digitOfElement = arr[j] % 10;
//            // 放入到对应桶中
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement] += 1;
//        }
//        // 根据桶顺序，放回数组
//        int index = 0;
//        // 遍历每个桶，并将桶中数据放入数组
//        for (int k = 0; k < bucket.length; k++) {
//            // 如果桶中有数据，我们才放入原数组
//            for(int l = 0; l < bucketElementCounts[k];l++){
//                // 取出元素到 原数组
//                arr[index] = bucket[k][l];
//                index++;
//            }
//            // bucketElementCounts元素置零
//            bucketElementCounts[k] = 0;
//
//        }
//        // 第二轮
//        for (int j = 0; j < arr.length;j++){
//            // 取出每个元素的十位
//            int digitOfElement = arr[j] / 10 % 10;
//            // 放入到对应桶中
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement] += 1;
//        }
//        // 根据桶顺序，放回数组
//        index = 0;
//        // 遍历每个桶，并将桶中数据放入数组
//        for (int k = 0; k < bucket.length; k++) {
//            // 如果桶中有数据，我们才放入原数组
//            for(int l = 0; l < bucketElementCounts[k];l++){
//                // 取出元素到 原数组
//                arr[index] = bucket[k][l];
//                index++;
//            }
//            // bucketElementCounts元素置零
//            bucketElementCounts[k] = 0;
//
//        }
//        // 第三轮
//        for (int j = 0; j < arr.length;j++){
//            // 取出每个元素的百位
//            int digitOfElement = arr[j] / 100 % 10;
//            // 放入到对应桶中
//            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
//            bucketElementCounts[digitOfElement] += 1;
//        }
//        // 根据桶顺序，放回数组
//        index = 0;
//        // 遍历每个桶，并将桶中数据放入数组
//        for (int k = 0; k < bucket.length; k++) {
//            // 如果桶中有数据，我们才放入原数组
//            for(int l = 0; l < bucketElementCounts[k];l++){
//                // 取出元素到 原数组
//                arr[index] = bucket[k][l];
//                index++;
//            }
//            // bucketElementCounts元素置零
//            bucketElementCounts[k] = 0;
//
//        }


    }

}
