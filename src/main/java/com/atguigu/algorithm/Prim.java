package com.atguigu.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import static com.atguigu.algorithm.KruskalCase.CHARS;
import static com.atguigu.algorithm.Prim.N;

/**
 * 普里姆算法
 * 最小生成树
 * 每一步拿到达过的节点去链接未连接的节点，每次取值最优的
 * BFS + 贪心
 *
 * @author 顾遥
 */
@Slf4j
public class Prim {
    static final int N = -1;
    public static final int[][] WEIGHT = new int[][]{
            {N, 5, 7, N, N, N, 2},
            {5, N, N, 9, N, N, 3},
            {7, N, N, N, 8, N, N},
            {N, 9, N, N, N, 4, N},
            {N, N, 8, N, N, 5, 4},
            {N, N, N, 4, 5, N, 6},
            {2, 3, N, N, 4, 6, N}
    };

    public static void main(String[] args) {
        char[] data = CHARS;
        int verxs = data.length;
        // 表示不可以连接
        int[][] weight = WEIGHT;
        MapGraph graph = new MapGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);

        minTree.showGraph(graph);
        log.info("普雷姆算法计算的最小路程数：{}" , minTree.prim(graph));
        KruskalCase kruskalCase = new KruskalCase(data, weight);
        log.info("克鲁斯卡尔算法得到最短路径数：{}",kruskalCase.kruskal());

    }

}

/**
 * 创建最小生成树
 */
@Slf4j
class MinTree {

    public int prim(MapGraph graph) {
        return prim(graph, 0);
    }

    /**
     * prim 算法获取最小生成树
     *
     * @param graph 图对象
     * @param v     开始节点
     * @return 最小路程数
     */
    public int prim(MapGraph graph, int v) {
        if (graph == null) {
            return 0;
        }
        // 确定是否访问过
        final boolean[] visited = new boolean[graph.verxs];
        // 已访问
        visited[v] = true;
        int h1 = -1, h2 = -1;
        int minWeight = N;
        int ans = 0;
        // 取到连接全部节点
        for (int k = 1; k < graph.verxs; k++) {
            // i 假定被访问过的
            for (int i = 0; i < graph.verxs; i++) {
                // j 假定未被访问过的
                for (int j = 0; j < graph.verxs; j++) {
                    // 符合条件且更小的路径
                    boolean lessWeight = minWeight == N || graph.weight[i][j] != N && graph.weight[i][j] < minWeight;
                    if (visited[i] && !visited[j] && lessWeight) {
                        // 替换 minWeight
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            log.debug("边<{},{}> 权值：{}", graph.data[h1], graph.data[h2], graph.weight[h1][h2]);
            ans += graph.weight[h1][h2];
            // 刷新访问
            visited[h2] = true;
            minWeight = N;

        }
        return ans;
    }

    /**
     * 创建图的邻接矩阵
     *
     * @param graph  图对象
     * @param verxs  顶点个数
     * @param data   顶点数据
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MapGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }

        }
    }

    /**
     * 展示图的邻接矩阵
     *
     * @param graph 图对象
     */
    public void showGraph(MapGraph graph) {
        for (int[] link : graph.weight) {

            System.out.println(Arrays.toString(link));
        }
    }
}


class MapGraph {
    /**
     * 节点个数，
     * 节点数据，
     * 存放边
     */
    int verxs;
    char[] data;
    int[][] weight;

    public MapGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
