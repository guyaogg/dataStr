package com.atguigu.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author 顾遥
 */
@Slf4j
public class Floyd {
    public static void main(String[] args) {
        char[] vertex = KruskalCase.CHARS;
        int[][] matrix = Prim.WEIGHT;

        Graph graph = new Graph(vertex, matrix);

        // 显示
        graph.show();

        // 使用弗洛伊德算法
        graph.floyd();

        int index = 5;
        log.info("从 {} 点到达所以节点的最短里程数为：{}", vertex[index],graph.floyd(index));

        // 显示
        graph.show();



    }

    private static class Graph {
        /**
         * 顶点
         */
        private final char[] vertex;
        /**
         * 邻接矩阵
         */
        private final int[][] dis;
        /**
         * 前驱节点,下标
         */
        private final int[][] pre;

        private boolean flag = false;

        private final static ReentrantLock LOCK = new ReentrantLock();


        public Graph(char[] vertex, int[][] dis) {
            int len = vertex.length;
            this.vertex = vertex.clone();
            this.dis = dis.clone();
            this.pre = new int[len][len];

//            初始化 pre
            for (int i = 0; i < pre.length; i++) {
                Arrays.fill(pre[i], i);
            }
        }

        public int floyd(int index){
            if(!flag){
                // 获取锁
                LOCK.lock();
                try {
                    // 临界区
                    if(!flag){
                        floyd();
                        flag = true;
                        log.debug("进行了弗洛伊德算法");
                    }
                }finally {
                    // 释放锁
                    LOCK.unlock();
                }
            }
            return Arrays.stream(dis[index]).sum();

        }

        public void floyd() {
            int len;
            // 对中间节点遍历
            for (int k = 0; k < dis.length; k++) {
                dis[k][k] = 0;
                // 出发节点
                for (int i = 0; i < dis.length; i++) {
                    for (int j = 0; j < dis.length; j++) {
                        // 保证是连接的
                        if (dis[i][k] != Prim.N && dis[k][j] != Prim.N) {
                            // 求出经过中间节点的距离
                            len = dis[i][k] + dis[k][j];
                            // 如果距离小于直连距离，或直连距离不存在，更新
                            if (len < dis[i][j] || dis[i][j] == Prim.N) {
                                // 更新距离
                                dis[i][j] = len;
                                // 更新前驱节点
                                pre[i][j] = pre[k][j];
                            }
                        }
                    }
                }
            }
        }

        /**
         * 显示 pre 数组和 dis 数组
         */
        public void show() {
            System.out.println("距离表");
            for (int[] di : dis) {
                System.out.println(Arrays.toString(di));
            }
            System.out.println("前驱节点");
            for (int[] ints : pre) {
                for (int anInt : ints) {
                    System.out.print(vertex[anInt] + "\t");
                }
                System.out.println();
            }
        }
    }
}


