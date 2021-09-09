package com.atguigu.sort;

import java.util.Arrays;

/**
 * @Description
 * @Author 顾遥
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1,-1,89};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 插入排序
     */
    public static void insertSort(int[] arr) {
        // 总结
        // 定插入值，索引
        int insertVal,insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                // insertVal前移,即arr[insertIndex]后移
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 插入,insertVal前移了才插入
            if(++insertIndex != i){
                arr[insertIndex] = insertVal;
            }

        }



        // 使用逐步推导
        // 第一轮
        // 定义待插入的数
//        int insertVal = arr[1];
        // 待插入数索引
//        int insertIndex = 1 - 1;
////        给insertVal找到插入位置
//        // 保证再给insertVal插入位置时不越界
//        // insertVal < arr[insertIndex]还没有找到插入位置
//        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
//            // arr[insertIndex]后移
//            arr[insertIndex + 1] = arr[insertIndex];
//            insertIndex--;
//        }
//        // 当退出while循环时，说明插入找到，位置在insertIndex + 1
//        if(arr[insertIndex + 1] != insertVal){
//            arr[insertIndex + 1] = insertVal;
//        }
//        System.out.println("第一轮插入后：" + Arrays.toString(arr));
//        // 第二轮
//        insertVal = arr[2];
//        insertIndex = 2 -1;
//        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
//            // arr[insertIndex]后移
//            arr[insertIndex + 1] = arr[insertIndex];
//            insertIndex--;
//        }
//        if(arr[insertIndex + 1] != insertVal){
//            arr[insertIndex + 1] = insertVal;
//        }
//        System.out.println("第二轮插入后：" + Arrays.toString(arr));
    }
}
