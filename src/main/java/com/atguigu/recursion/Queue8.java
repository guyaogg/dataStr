package com.atguigu.recursion;

import java.util.Arrays;

/**
 * @Description
 * @Author 顾遥
 */
public class Queue8 {
    /**
     * 定义一个max表示共有多少个皇后
     */
    private int max = 8;
    /**
     * 定义数组array，保存皇后位置的结果，
     */
    private int[] array = new int[max];
    /**
     * 记录可能的数量
     */
    private int num = 0;
    /**
     * 记录判断总数
     */
    int count = 0;
    public static void main(String[] args) {
        // 测试，
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("=============================");
        System.out.println("总数为：" + queue8.getNum());
        System.out.println("判断总数为：" + queue8.count);


    }
    public int getNum(){
        return num;
    }

    /**
     * 编写一个方法，防止第n个皇后
     */
    private void check(int n){
        if(n == max){
            // 放置完毕
            print();
            num++;
            return;
        }
        // 依次放置皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前皇后，放到该行的第一列
            array[n] = i;
            // 判断第n个皇后到第i列是否冲突
            if(judge(n)){
                // 不冲突,即开始递归
                check(n + 1);
            }
            // 如果冲突，就继续执行n + 1列的皇后
        }
    }

    /**
     * 查看当我们放置第n皇后，就去检查该皇后是否和前面已经摆放的皇后冲突
     * @param n 表示第n个皇后
     */
    private boolean judge(int n){
        count++;
        for(int i = 0; i< n;i++){
            // 1 表示同一列， 2表示统一斜线
            if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
    /**
     *     写一个方法，可以将皇后拜访位置输出
      */
    private void print(){
        System.out.println(Arrays.toString(array));
        System.out.println("--------------------------");
    }

}
