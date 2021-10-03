package com.atguigu.algorithm;

/**
 * 01 背包问题，动态规划算法
 *
 * @author 顾遥
 */
public class KnapsackProblem {
    public static void main(String[] args) {

        // 物品重量
        int[] w = {1, 4, 3,6,8};
        // 物品价值
        int[] v = {1500, 3000, 2000,6000,8500};
        // 背包容量
        int m = 10;

        System.out.println(knapsackMaxValue(w, v, m));

    }

    /**
     * @param w 物品重量
     * @param v 物品价值
     * @param m 背包容量
     * @return 最大背包价值，物品都为 1
     */
    public static int knapsackMaxValue(int[] w, int[] v, int m) {
        int[][] ans = new int[w.length + 1][m + 1];
        boolean[][] path = new boolean[w.length + 1][m + 1];
        // 0 行 0 列默认为0；
        for (int i = 1; i < ans.length; i++) {
            for (int j = 1; j < ans[i].length; j++) {
                // 存不下，当前重量，继承上一个容量
                if (j < w[i - 1]) {
                    ans[i][j] = ans[i - 1][j];
                }
                // 存的下，当前重量，同上一个容量比较加入
                else {
                    if(ans[i - 1][j] > v[i - 1] + ans[i - 1][j - w[i - 1]]){
                        ans[i][j] =  ans[i - 1][j];
                    }else {
                        ans[i][j] = v[i - 1] + ans[i - 1][j - w[i - 1]];
                        path[i][j] = true;
                    }
                }
            }
        }
        int pathM = w.length;
        int pathN = m;
        while (pathN > 0 && pathM > 0){
            if(path[pathM][pathN]){
                System.out.println("背包放入了第 " + pathM + " 个商品");
                pathN -= w[pathM - 1];
            }
            pathM--;
        }
        return ans[w.length][m];
    }

}
