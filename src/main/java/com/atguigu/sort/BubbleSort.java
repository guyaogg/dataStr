package com.atguigu.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @Description
 * @Author 顾遥
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3,9,-1,10,20};
//        System.out.println("排序前.");
//        System.out.println(Arrays.toString(arr));
//        System.out.println("测试冒泡排序");
        // 测试冒泡复杂度O(n^2),给80000个数据，测试
//        排序用时：11054 ms,合计 11 秒O(n^2)
//        现排序用时算法：        排序用时：48 ms,合计 0 秒 O(nlogn)
        // O(n) 0ms
        // 创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            // 随机生产0-80000000的数
            arr[i] = (int) (Math.random() * 8000000);
        }
        long begin = System.currentTimeMillis();

        bubbleSort(arr);
//        Arrays.sort(arr);
//        int temp;
//        for (int j = 0; j < arr.length - 1; j++) {
//            temp = arr[j];
//            arr[j] = arr[j + 1];
//            arr[j + 1] = temp;
//        }
        long end = System.currentTimeMillis();
        long time = end - begin;
        System.out.printf("排序用时：%d ms,合计 %d 秒",time,time/1000);
        System.out.println();
//        System.out.println("排序后...........");
//        System.out.println(Arrays.toString(arr));

//        for (int i = 0; i < arr.length - 1; i++) {
//            // 如果前面数比后面数就交换
//            if(arr[i] > arr[i + 1]){
//                temp = arr[i];
//                arr[i] = arr[i + 1];
//                arr[i + 1] = temp;
//            }
//
//
//        }
//        System.out.println("第一次排序后的数组");
//        System.out.println(Arrays.toString(arr));
//
//
//        // 第二次排序，就是把第二大的数排在最后倒数第二位
//        for (int i = 0; i < arr.length - 2; i++) {
//            // 如果前面数比后面数就交换
//            if(arr[i] > arr[i + 1]){
//                temp = arr[i];
//                arr[i] = arr[i + 1];
//                arr[i + 1] = temp;
//            }
//
//        }
//        System.out.println("第二次排序后的数组");
//        System.out.println(Arrays.toString(arr));




    }
    /**
     * 将冒泡排序封装
     */
    public static void bubbleSort(int[] arr){
        int temp;
        // 标识变量，表示是否进行过交换
        boolean flag = false;
        // 冒泡排序的时间复杂度（O(n^2)）
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if(arr[j] > arr[j + 1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(!flag){
                // 一次交换都没有发生
                break;
            }else {
                // 重置flag，进行下次判断
                flag = false;
            }
//            System.out.println("第"+i+"次排序后的数组");
//            System.out.println(Arrays.toString(arr));

        }
//        System.out.println("排序后的数组");
//        System.out.println(Arrays.toString(arr));
    }
}
