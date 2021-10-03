package com.atguigu.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description
 * @Author 顾遥
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 456, 1};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static <T> void quickSort(T[] arr, Comparator<? super T> c) {
        sort(arr, 0, arr.length - 1, c);
    }

    private static <T> void sort(T[] arr, int left, int right, Comparator<? super T> c) {
        // 左下标
        int l = left;
        // 右下标
        int r = right;
        // 中轴位置
        T pivot = arr[l + (r - l) / 2];
        // 临时变量交换时使用
        T temp;
        // 循环目的：让pivot小的值放到左边，比pivot大的值放到右边
        while (l < r) {
            while (c.compare(arr[l], pivot) < 0) {
                // 在左边找到大于等于中轴位置的值才退出
                l += 1;
            }
            while (c.compare(arr[r], pivot) > 0) {
                // 在右r边找到小于等于中轴位置的值才退出
                r -= 1;
            }
            // 当l >= r，说明pivot左边全部小于等于pivot，而右边全部大于等于pivot
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后，发现arr[l] == pivot,让r--
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换完后，发现arr[r] == pivot,让l++
            if (arr[r] == pivot) {
                l++;
            }


        }
        // 如果l == r，必须l++,r--,否则栈溢出
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            sort(arr, left, r, c);
        }
        if (right > l) {
            sort(arr, l, right, c);
        }
//        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int left, int right) {
        // 左下标
        int l = left;
        // 右下标
        int r = right;
        // 中轴位置
        int pivot = arr[l + (r - l) / 2];
        // 临时变量交换时使用
        int temp = 0;
        // 循环目的：让pivot小的值放到左边，比pivot大的值放到右边
        while (l < r) {
            while (arr[l] < pivot) {
                // 在左边找到大于等于中轴位置的值才退出
                l += 1;
            }
            while (arr[r] > pivot) {
                // 在右r边找到小于等于中轴位置的值才退出
                r -= 1;
            }
            // 当l >= r，说明pivot左边全部小于等于pivot，而右边全部大于等于pivot
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后，发现arr[l] == pivot,让r--
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换完后，发现arr[r] == pivot,让l++
            if (arr[r] == pivot) {
                l++;
            }


        }
        // 如果l == r，必须l++,r--,否则栈溢出
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            sort(arr, left, r);
        }
        if (right > l) {
            sort(arr, l, right);
        }
//        System.out.println(Arrays.toString(arr));


    }
}
