package com.atguigu.algorithm;

/**
 * 二分查找算法
 * @author 顾遥
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {

        // 测试
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 50);
        System.out.println("index = " + index);

    }

    /**
     * 二分查找算法实现
     * @param arr 待查找的数组, 注意数组是 <h1>升序的</h1>
     * @param target 需要查找的数
     * @return 对应下标，-1 代表未查找到
     */
    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;
        while (right >= left){
            int mid = left + (right - left) / 2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                // 向左查找
                right = mid - 1;
            }else {
                // 向右查找
                left = mid + 1;
            }
        }
        return -1;
    }
}
