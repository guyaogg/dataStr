package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 二分查找
 *  前提数组为有序的
 * @Author 顾遥
 */
public class BinarySearch implements SearchSetMid{
    public static void main(String[] args) {
//        int[] arr = {1, 8, 10, 89, 1000,1000, 1234};
        int[] arr = {1, 55,810, 999, 999, 1000,1000, 1000,1000, 1000,1000,1000,1000,1000,1000,1234};


        List list = binarySearch2(arr, 1000);
        System.out.println(list);
    }

    /**
     * 查找单个符合目标
     * @param arr 查找有序数组对象
     * @param target 要查找的值
     * @return
     */
    public static int binarySearch(int[] arr,int target){
       return Search.search(arr, target,new BinarySearch());
    }

    //{1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，有多个相同的数值时，
    // 如何将所有的数值都查找到，比如这里的 1000.
    /**
     *
     * 二分查找 查找目标值的索引集合
     * @param arr 查找有序数组对象
     * @param left 左边索引
     * @param right 右边索引
     * @param target 要查找的值
     * @return
     */
//    public static List<Integer> binarySearch2(int[] arr, int left, int right, int target){
//        // 未查找到
//        if(left > right || target < arr[0] || target > arr[arr.length - 1]){
//            return new ArrayList<>();
//        }
//        int mid = left + (right - left) / 2;
//        if(target > arr[mid]){
//            return binarySearch2(arr, mid + 1, right, target);
//        }else if(target < arr[mid]){
//            return binarySearch2(arr, left, mid - 1, target);
//        }else{
//            List<Integer> resList = new ArrayList<>();
//            resList.add(mid);
//            // 向左向右遍历
//            int temp = mid - 1;
//            while(temp >= 0 && arr[temp] == target){
//                resList.add(temp);
//                temp--;
//            }
//            temp = mid + 1;
//            while(temp < arr.length && arr[temp] == target){
//                resList.add(temp);
//                temp++;
//            }
//            return resList;
//
//        }
//    }
    /**
     *
     * 二分查找 查找目标值的索引集合
     * @param arr 查找有序数组对象
     * @param target 要查找的值
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int target){
        return Search.listSearch(arr, target,new BinarySearch());
    }


    @Override
    public int setMid(int[] arr, int left, int right, int target) {
        // 取二分中值

        return left + (right - left) / 2;
    }
}
