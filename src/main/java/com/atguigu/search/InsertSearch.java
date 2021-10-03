package com.atguigu.search;

import java.util.Arrays;
import java.util.List;

/**
 * @Description 插值查找算法
 * 数组有序，最好等差
 * @Author 顾遥
 */
public class InsertSearch implements SearchSetMid{
    public static void main(String[] args) {
//        int[] arr = new int[100];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = i + 1;
//        }
//        int[] arr = {1, 8, 10, 89, 1000,1000, 1234};
        int[] arr = {1, 55,810, 999, 999, 1000,1000, 1000,1000, 1000,1000,1000,1000,1000,1000,1234};

//        int insertValueSearch = insertValueSearch(arr, 1);
        List insertValueSearch = listInsertSearch(arr, 1000);
        System.out.println(insertValueSearch);

//        System.out.println(Arrays.toString(arr));
    }

    public static int insertValueSearch(int[] arr,int target){

        return Search.search(arr, target, new InsertSearch());
    }
    public static List<Integer> listInsertSearch(int[] arr,int target){
        return Search.listSearch(arr, target, new InsertSearch());
    }
//    /**
//     * 插值查找
//     */
//    public static int insertValueSearch(int[] arr,int left,int right,int target){
//        System.out.println("查了");
//        // 未查找到 target < arr[0] || target > arr[arr.length - 1]必须有，否则mid会越界
//        if(left > right || target < arr[0] || target > arr[arr.length - 1]){
//            return -1;
//        }
//        int mid = left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]);
//        if(target > arr[mid]){
//            return insertValueSearch(arr, mid + 1, right, target);
//        }else if(target < arr[mid]){
//            return insertValueSearch(arr, left, mid - 1, target);
//        }else{
//            return mid;
//        }
//    }

    @Override
    public int setMid(int[] arr, int left, int right, int target) {
        // 按比例取mid值
        return left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]);
    }
}
