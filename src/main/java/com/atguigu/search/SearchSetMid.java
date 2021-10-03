package com.atguigu.search;

/**
 * @Description 查找需求的mid函数式方法
 * @Author 顾遥
 */
@FunctionalInterface
public interface SearchSetMid {
    /**
     * mid取值的方法，mid位置算法
     * @param arr 查找数组对象
     * @param left 左索引
     * @param right 右索引
     * @param target 目标值
     * @return
     */
     int setMid(int[] arr,int left,int right,int target);
}
