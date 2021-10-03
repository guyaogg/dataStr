package com.atguigu.recursion;

import java.util.Arrays;

/**
 * @Description 迷宫类
 * @Author 顾遥
 */
public class MiGong {
    public static void main(String[] args) {
        // 先创建二维数组，模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用1表示墙
        // 上下全部置为1；
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 1; i < 7; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置相应的挡板
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;
        // 输出地图
        System.out.println("地图的情况");
        Arrays.stream(map).forEach(m -> {
            System.out.println(Arrays.toString(m));
        });
        // 使用递归回溯给小球找路
//        setWay(map, 1, 1);
        // 改变策略
        setWay2(map, 1, 1);

        // 输出新的地图，小球走过并标识的地图
        System.out.println("小球走过并标识的地图");
        Arrays.stream(map).forEach(m -> {
            System.out.println(Arrays.toString(m));
        });

    }


    /**
     * 修改找路的策略，上右下左
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                // 如果当前这个点还没有走过
                // 下->右->上->左
                // 假定该点可以走通
                map[i][j] = 2;
                if (setWay2(map, i - 1, j)) {
                    // 如果走通,返回true
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    return true;
                } else if (setWay2(map, i + 1, j)) {
                    return true;
                } else if (setWay2(map, i, j - 1)) {
                    return true;
                } else {
                    // 该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // map[i][j]不为0，可能1，2，3
                return false;
            }
        }

    }

    /**
     * map 表示地图，i、j表示从哪里出发 （1，1）
     * 如果能到达（6，5） 即成功
     * 约定：map[i][j]为0时表示没有走过，1表示墙不能走，2表示可以走，3表示该点走过，但是走不通
     * 再走迷宫时，需要确定一个策略（方法）下->右->上->左,如果该点走不通，再回溯
     *
     * @param map 地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 找到了为true 否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                // 如果当前这个点还没有走过
                // 下->右->上->左
                // 假定该点可以走通
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    // 如果走通,返回true
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    // 该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // map[i][j]不为0，可能1，2，3
                return false;
            }
        }

    }
}
