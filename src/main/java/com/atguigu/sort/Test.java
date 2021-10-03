package com.atguigu.sort;

import java.util.Arrays;

/**
 * @Description
 * @Author 顾遥
 */
public class Test {
    public static void main(String[] args) {
        // 测试复杂度,给80000个数据，测试,,JVM能够调优0.1秒
//        冒泡排序用时：11054 ms,合计 11 秒O(n^2)
//        简单选择：排序用时：3067 ms,合计 3 秒O(n^2)
//        直接插入：排序用时：639 ms,合计 0.6 秒O(n^2)
//        希尔交换式：排序用时：7201 ms,合计 7 秒 O(n^2) 交换式希尔
//        希尔移动式：排序用时：21 ms,合计 0 秒O(nlogn) 移动式希尔.小于十万厉害
//        快速排序：排序用时：28 ms,合计 0 秒O(nlogn)，平均快（十万-一百万）
//        归并排序：排序用时：19 ms,合计 0 秒O(nlogn)，会有额外的一倍空间
//        基数排序：排序用时：23 ms,合计 0 秒O(n*k)(8000000排序用时：610 ms ，会有十倍额外空间
//        堆排序：排序用时：18 ms,合计 0 秒O(nlogn)
//        现官方排序用时算法：        排序用时：48 ms,合计 0 秒 O(nlogn)
        // O(n) 0ms
        // 创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            // 随机生产0-80000000的数
            arr[i] = (int) (Math.random() * 8000000);
        }
        int[] clone = arr.clone();

        long begin = System.currentTimeMillis();

//        BubbleSort.bubbleSort(arr);  // 排序用时：11054 ms,合计 11 秒O(n^2)
//        SimpleSelectSort.selectSort(arr); // 排序用时：3067 ms,合计 3 秒O(n^2) 比冒泡交换少
//        InsertSort.insertSort(arr); // 排序用时：639 ms,合计 0 秒O(n^2) 比直接选择快
//        ShellSort.shellSort1(arr); // 排序用时：7201 ms,合计 7 秒 交换式希尔.效率不高，交换太多,实际倒叙冒泡的优化
//        ShellSort.shellSort2(arr); // 排序用时：21 ms,合计 0 秒O(nlogn)移动式希尔，复杂度明显降低，芜湖~  (8000000排序用时：2297 ms,合计 2 秒（数据小快）
//        QuickSort.quickSort(arr); // 排序用时：28 ms,合计 0 秒O(nlogn) (8000000排序用时：1317 ms,合计 1 秒 较大数据时候厉害（平均快）
//        MergeSort.mergeSort(arr); // 排序用时：19 ms,合计 0 秒O(nlogn) (8000000排序用时：1262 ms,合计 1 秒 内存消耗1倍
//        RadixSort.radixSort(arr); // 排序用时：23 ms,合计 0 秒O(n*k)(8000000排序用时：610 ms,合计 0 秒 内存消耗10倍
//        HeapSort.heapSort(arr);// 排序用时：18 ms,合计 0 秒O(nlogn)(8000000排序用时：2059 ms,合计 2 秒 适合数据量小的排序
//        Arrays.sort(arr);   // 现排序用时算法：        排序用时：48 ms,合计 0 秒 O(nlogn)
//        MeS(arr); // 排序用时：16 ms,合计 0 秒
//        int temp;
//        for (int j = 0; j < arr.length - 1; j++) {
//            temp = arr[j];
//            arr[j] = arr[j + 1];
//            arr[j + 1] = temp;
//        }
        long end = System.currentTimeMillis();
        long time = end - begin;
        System.out.printf("排序用时：%d ms,合计 %d 秒", time, time / 1000);
        System.out.println();
        Arrays.sort(clone);
        System.out.println(Arrays.equals(arr, clone));
    }

    public static void MeS(int[] arr) {
        int[] temp = new int[arr.length];
        MeS(arr, 0, arr.length - 1, temp);
    }

    private static void MeS(int[] arr, int left, int right, int[] temp) {
        // 分
        if (left < right) {
            int mid = left + (right - left) / 2;
            MeS(arr, left, mid, temp);
            MeS(arr, mid + 1, right, temp);
            MeS(arr, left, mid, right, temp);
        }
    }

    private static void MeS(int[] arr, int left, int mid, int right, int[] temp) {
        // 合
        int i = left;
        int j = mid + 1;

        int index = 0;
        while (i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[index++] = arr[i++];
            }else {
                temp[index++] = arr[j++];
            }
        }

        while(i <= mid){
            temp[index++] = arr[i++];
        }
        while (j <= right){
            temp[index++] = arr[j++];
        }

        index = 0;
        for (int k = left; k <= right; k++) {
            arr[k] = temp[index++];
        }
    }
}
