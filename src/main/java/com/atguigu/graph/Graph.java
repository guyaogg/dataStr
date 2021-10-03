package com.atguigu.graph;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author 顾遥
 */
@Slf4j
public class Graph<T> {

    /**
     * 测试
     */
    public static void main(String[] args) {
        int n = 5;
        String[] vertexes = {"A", "B", "C", "D", "E"};
        Graph<String> graph = new Graph<>(n);
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        // 添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);


        // 显示
        graph.show();

        // dfs
        log.info("深度遍历~");
        graph.dfs();
        System.out.println();
        // dfs
        log.info("广度遍历~");
        graph.bfs();

    }


    /**
     * 存储顶点集合
     */
    private List<T> vertexList;

    /**
     * 存储图的邻接矩阵
     */
    private int[][] edges;

    /**
     * 表示边的数目
     */
    private int numOfEdges;


    /**
     * 构造器
     *
     * @param n 顶点个数
     */
    public Graph(int n) {
        // 初始化矩阵和 vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }


    /**
     * 得到第一个邻接点的下标
     *
     * @param index 邻接点的发起节点
     * @return 如果存在返回对应邻接点下标，否则返回 -1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < edges[index].length; j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接点的下标，来获取下一个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < edges[v1].length; j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //图中常用方法


    /**
     * BFS
     */
    public void bfs() {
        int n = getNumOfVertex();
        boolean[] isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * BFS 对一个节点
     */
    private void bfs(boolean[] isVisited, int i) {
        int u, w;
        // 队列,记录访问顺序
        Queue<Integer> queue = new LinkedList<>();
        // 访问节点
        System.out.print(getValueByIndex(i) + "=>");
        // 标记已访问
        isVisited[i] = true;
        queue.offer(i);
        while (!queue.isEmpty()) {
            u = queue.poll();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    queue.offer(w);
                } else {
                    // 找下一个邻接点
                    w = getNextNeighbor(u, w);
                }
            }
        }
    }

    public void dfs() {
        int n = getNumOfVertex();
        boolean[] isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * DFS
     *
     * @param isVisited 记录访问
     * @param i         第一次为 0
     */
    private void dfs(boolean[] isVisited, int i) {
        // 定义个数组 boolean[] ，记录是否被访问

        System.out.print(getValueByIndex(i) + "->");
        // 将这个节点已经访问过
        isVisited[i] = true;

        // 查找第一个邻接节点 w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果 w 已经被访问过
            else {
                w = getNextNeighbor(i, w);
            }
        }
    }

    /**
     * 节点数量
     *
     * @return 节点数量
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 得到边的数量
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回节点对应的数据
     *
     * @param i 顶点下标
     * @return 顶点的数据
     */
    public T getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回 v1 和 v2 的权值
     *
     * @param v1 下标
     * @param v2 另一个下标
     * @return
     */
    public int getWight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图对应的矩阵
     */
    public void show() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }


    /**
     * 插入顶点
     */
    public void insertVertex(T vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标，即第几个顶点 "A" - "B" "A" -> 0,"B" -> 1
     * @param v2     表示第二个点的下标
     * @param weight 表示关联（标识关联
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
