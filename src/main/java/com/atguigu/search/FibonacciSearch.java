package com.atguigu.search;

import java.util.Arrays;

/**
 * @Description 斐波那契查找算法
 * @Author 顾遥
 */
public class FibonacciSearch implements SearchSetMid {
    public static final int MAX_SIZE = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        int i = fibSearch(arr, 1234);
        System.out.println(i);
    }
    // 因为后来我们mid = low + F(k - 1) - 1,需要斐波那契数列，因此要先获取此数列

    /**
     * 非递归方法得到获取斐波那契数列
     *
     * @return 斐波那契数列
     */
    public static int[] fib() {
        int[] f = new int[MAX_SIZE];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < MAX_SIZE; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int yaoSearch(int[] arr, int target) {
        return Search.search(arr, target, new FibonacciSearch());
    }

    /**
     * 斐波那契查找
     * 非递归方式编写算法
     *
     * @param arr
     * @param target
     * @return
     */
    public static int fibSearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        // 分割下标
        int k = 0;
//        存放mid值
        int mid = 0;
        // 获取数列
        int[] f = fib();
        // 获取分割坐标
        while (right > f[k] - 1) {
            k++;
        }
//        因为f[k]值可能大于arr的长度，使用Arrays类，构造一个新数组，指向arr
//        不足部分会使用0填充
//       现在 arr = {1, 8, 10, 89, 1000,1000, 1234 ,0 ,0};
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 实际上需要arr数组最后的数填充temp
        // 实际上 arr = {1, 8, 10, 89, 1000,1000, 1234 ,1234 ,1234};
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = arr[right];
        }
        while (left <= right) {
            mid = left + f[k - 1] - 1;
            if (target < temp[mid]) {
                // 我们应该继续向数组左边查找
                right = mid - 1;
                // 为什么是k--;
                // 全部元素 = 前面元素 + 后面元素
                // f[k] = f[k - 1] + f[k - 2]
                // 因为前面有f[k - 1]个元素，所有可以继续拆分 f[k - 1] = f[k - 2] + f[k - 3]
                // 即在f[k - 1]的前面继续查找即k--
                // 即下次mid = f[k - 1 - 1] - 1;
                k--;
            } else if (target > temp[mid]) {
                // 我们应该去右边查找
                left = mid + 1;
                // 为什么是k -= 2呢
                // 全部元素 = 前面元素 + 后面元素
                // f[k] = f[k - 1] + f[k - 2]
                // 因为后面是 f[k -2]元素，所有拆分 f[k - 2] = f[k - 3] + f[k - 4]
                // 即在f[k - 2]的前面可以继续查找
                // 即下次循环mid = f[k - 1 - 2] - 1
                k -= 2;
            } else {
                // 找到
                if (mid <= right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        return -1;

    }

    @Override
    public int setMid(int[] arr, int left, int right, int target) {
        // 黄金分割比例
        return (int)(left + (right - left) * 0.618);
    }
}
