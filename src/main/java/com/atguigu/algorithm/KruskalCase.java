package com.atguigu.algorithm;

import com.atguigu.sort.QuickSort;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 克鲁斯卡尔算法
 * 同上处理最小生成树
 * 按照权值从小到大的顺序选择 n - 1 条边
 * 并保证 n - 1 条边不构成回路
 * 公交站问题
 *
 * @author 顾遥
 */
@Slf4j
public class KruskalCase {
    public static final char[] CHARS = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    public static void main(String[] args) {
        char[] verxs = CHARS;
        int[][] matrices = new int[][]{

                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/{12, 0, 10, INF, INF, 7, INF},
                /*C*/{INF, 10, 0, 3, 5, 6, INF},
                /*D*/{INF, INF, 3, 0, 4, INF, INF},
                /*E*/{INF, INF, 5, 4, 0, 2, 8},
                /*F*/{16, 7, 6, INF, 2, 0, 9},
                /*G*/{14, INF, INF, INF, 8, 9, 0}
        };

        KruskalCase kruskalCase = new KruskalCase(verxs, matrices);
        kruskalCase.print();

        // 测试排序
        log.debug("未排序");
        EdgeData[] edges = kruskalCase.getEdges();
        log.info(Arrays.toString(edges));
        kruskalCase.sortEdges(edges);
        log.debug("已排序");
        log.info(Arrays.toString(edges));


        int kruskal = kruskalCase.kruskal();
        log.info("克鲁斯卡尔算法得到最短路径数：{}", kruskal);


    }

    /**
     * 有效边的个数(除去重复 和 自连接
     */
    private int edgeNum;

    /**
     * 顶点数组
     */
    private final char[] verxs;
    /**
     * 邻接矩阵
     */
    private final int[][] matrixes;

    /**
     * 表示不能连接
     */
    private static final int INF = -1;


    /**
     * 构造器
     *
     * @param verxs    顶点数组
     * @param matrixes 邻接矩阵
     */
    public KruskalCase(char[] verxs, int[][] matrixes) {
        this.verxs = verxs.clone();
        this.matrixes = matrixes.clone();

        for (int i = 0; i < matrixes.length; i++) {
            for (int j = i + 1; j < matrixes[i].length; j++) {
                if (matrixes[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    /**
     * 使用 处理最小生成树
     * 处理最小生成树
     *
     * @return 最小路程数
     */
    public int kruskal() {
        int index = 0;
        // 保存终点的数组
        int[] ends = new int[edgeNum];
        // 创建数组保存最后的最小生成树
        EdgeData[] rets = new EdgeData[edgeNum];
        int ans = 0;

        // 读取图中所有有效边的集合，一共有 12 边
        EdgeData[] edges = getEdges();

        // 有效边数组排序
        sortEdges(edges);

        // 遍历 edges 数组，添加到最小生成树，且过程中不能有回路
        for (EdgeData edge : edges) {
            // 获取每条边的顶点,终点
            int p1 = getPosition(edge.start);
            int p2 = getPosition(edge.end);

            // 获取 p1,p2 在已有生成树的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);

            // 是否构成回路
            if (m != n) {
                // 记录终点等
                ends[m] = n;
                rets[index++] = edge;
                ans += edge.weight;
            }
        }
        log.debug("最小生成数为");
        for (int i = 0; i < index; i++) {

            log.debug("\t - {}", rets[i]);

        }
        return ans;
    }

    /**
     * 获取顶点的终点
     *
     * @param ends 遍历过程中逐步形成的终点
     * @param i    传入顶点的下标
     * @return 下标为 i 对应的终点下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 获取图中的边，放入 EdgeData[] 数组中，后来需要遍历该数组
     * 通过 matrixes 邻接矩阵得到
     *
     * @return 有效边数组
     */
    public EdgeData[] getEdges() {
        int index = 0;
        EdgeData[] edges = new EdgeData[edgeNum];
        for (int i = 0; i < verxs.length; i++) {
            for (int j = i + 1; j < verxs.length; j++) {
                if (matrixes[i][j] != INF) {
                    edges[index++] = new EdgeData(verxs[i], verxs[j], matrixes[i][j]);
                }

            }
        }
        return edges;

    }

    /**
     * 获取 ch 顶点的下标
     *
     * @param ch 顶点的值
     * @return 顶点的下标，没找到返回 -1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < verxs.length; i++) {
            if (verxs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 对边进行排序
     *
     * @param edges 边对象
     */
    private void sortEdges(EdgeData[] edges) {
        QuickSort.quickSort(edges, Comparator.comparingInt(e -> e.weight));
    }

    public void print() {
        System.out.println("邻接矩阵：\n");
        for (int[] matrix : matrixes) {

            for (int edge : matrix) {
                System.out.printf("%7d\t", edge);
            }
            System.out.println();
        }
    }


}

class EdgeData {
    /**
     * 边的起点
     */
    char start;
    /**
     * 边的终点
     */
    char end;

    int weight;

    public EdgeData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        // 边<A,G> 权值：2
        return "边<" + start +
                "," + end +
                ">  权值：" + weight;
    }
}


