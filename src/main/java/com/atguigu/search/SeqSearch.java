package com.atguigu.search;

import java.util.Arrays;

/**
 * @Description
 * @Author 顾遥
 */
public class SeqSearch {
    public static void main(String[] args) {
        // 没有顺序的数组
        int[] arr = {1, 9, 11, -1, 34, 89};
        int[] search = seqSearch(arr, new int[]{9, 89, 2, -1, 6, 6, -8, 1, 1});
        System.out.println(Arrays.toString(search));

    }

    /**
     * 线性查找，逐一比对
     *
     * @param arr 查找数组对象
     * @param target 查找目标对象
     * @return 返回索引值, -1为未找到
     */
    public static int seqSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;

    }

    /**
     * 查找目标数组
     * @param arr
     * @param target
     * @return
     */
    public static int[] seqSearch(int[] arr, int[] target) {
        int[] ans = new int[target.length];
        boolean logo = false;
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == target[i]) {
                    ans[i] = j;
                    logo = true;
                }
            }
            if(!logo){
                ans[i] = -1;
            }
            logo = false;
        }

        return ans;

    }
}
