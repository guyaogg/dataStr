package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 以二分查找为例的通用查找方法
 * @Author 顾遥
 */
public class Search {
    /**
     * 查找目标索引值,左默认0，右默认最大索引
     * @param arr    有序的目标数组
     * @param target 目标值
     * @param setMid mid的求取方法
     * @return
     */
    public static int search(int[] arr, int target, SearchSetMid setMid) {
        return search(arr, 0, arr.length - 1, target, setMid);
    }

    /**
     * 搜查单个值方法
     *
     * @param arr    目标数组
     * @param left 左侧索引值
     * @param right 右侧索引值
     * @param target 目标值
     * @param setMid mid的求取方法
     * @return
     */
    private static int search(int[] arr, int left, int right, int target, SearchSetMid setMid) {
//        System.out.println("查了");
        // 未查找到
        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }
        int mid = setMid.setMid(arr, left, right, target);
        if (target > arr[mid]) {
            return search(arr, mid + 1, right, target, setMid);
        } else if (target < arr[mid]) {
            return search(arr, left, mid - 1, target, setMid);
        } else {
            return mid;
        }
    }

    /**
     * 查找目标集合
     * @param arr    有序的目标数组
     * @param target 目标值
     * @param setMid mid的求取方法
     * @return
     */
    public static List<Integer> listSearch(int[] arr, int target, SearchSetMid setMid) {
        return listSearch(arr, 0, arr.length - 1, target, setMid);
    }


    /**
     *查找目标集合
     *
     * @param arr    目标数组
     * @param left 左侧索引值
     * @param right 右侧索引值
     * @param target 目标值
     * @param setMid mid的求取方法
     * @return
     */
    private static List<Integer> listSearch(int[] arr, int left, int right, int target, SearchSetMid setMid) {
        // 未查找到
        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return new ArrayList<>();
        }
        int mid = left + (right - left) / 2;
        if (target > arr[mid]) {
            return listSearch(arr, mid + 1, right, target, setMid);
        } else if (target < arr[mid]) {
            return listSearch(arr, left, mid - 1, target, setMid);
        } else {
            List<Integer> resList = new ArrayList<>();
            resList.add(mid);
            // 向左向右遍历
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == target) {
                resList.add(temp);
                temp--;
            }
            temp = mid + 1;
            while (temp < arr.length && arr[temp] == target) {
                resList.add(temp);
                temp++;
            }
            return resList;

        }
    }
}
