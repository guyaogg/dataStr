package com.atguigu.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 * 介绍
 * BFS 应用
 * 根据某顶点生成最小生成树
 *
 * @author 顾遥
 */
@Slf4j
public class Dijkstra {


    public static void main(String[] args) {
        char[] vertex = KruskalCase.CHARS;
        int[][] matrices = Prim.WEIGHT;
        Graph graph = new Graph(vertex, matrices);

        // 查看图
        graph.showGraph();

        // 测试 djs
        int index = 5;
        int dsj = graph.dsj(index);
        log.info("从 {} 点到达所以节点的最短里程数为：{}", vertex[index],dsj);


        // 展示
        graph.showDjs();


    }
}

class Graph {
    final char[] vertex;
    final int[][] matrix;
    private  VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex.clone();
        this.matrix = matrix.clone();
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉算法 实现
     *
     * @param index 出发顶点下标
     * @return 通过一个顶点到达全部节点的最小路程数, -1 表示不能走完全部历程
     */
    public int dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        // 更新首个 index 顶点相关配置
        update(index);

        for (int j = 1; j < vertex.length; j++) {
            // 更新访问顶点
            index = vv.updateArr();
            update(index);
        }
        int ans = 0;
        for (int i = 0; i < vertex.length; i++) {
            int now = vv.getDis(i);
            if(now == Integer.MAX_VALUE){
                return -1;
            }
            ans += now;
        }
        return ans;
    }


    /**
     * 更新 当前顶点到周围顶点的距离，和周围节点的前驱节点
     *
     * @param index 出发顶点下标
     */
    private void update(int index) {
        int len;
        //
        for (int j = 0; j < matrix[index].length; j++) {
            // 如果能联通
            if(matrix[index][j] != Prim.N){
                // len 当前到周围顶点的距离
                len = vv.getDis(index) + matrix[index][j];
                // j 没有访问过 && 小于出发顶点的距离
                if(vv.isVisited(j) && len <  vv.getDis(j)){
                    // 更新前驱，和距离
                    vv.updatePre(j, index);
                    vv.updateDis(j, len);
                }
            }

        }
    }
    public void showDjs(){
        vv.show();
    }

}

/**
 * 已访问节点集合
 */
class VisitedVertex {
    final int[] alreadyArr;
    final int[] preVisited;
    final int[] dis;

    /**
     * @param length 表示顶点个数
     * @param index  出发顶点对应的下标
     */
    public VisitedVertex(int length, int index) {
        alreadyArr = new int[length];
        preVisited = new int[length];
        dis = new int[length];

//        初始化 dis 数组,默认最大值，到达不了
        Arrays.fill(dis, Integer.MAX_VALUE);

        // 设置出发顶点
        alreadyArr[index] = 1;
        dis[index] = 0;
    }

    /**
     * 判断 index 顶点是否被访问过 already
     *
     * @param index 顶点下标
     * @return true 访问过
     */
    public boolean isVisited(int index) {
        return alreadyArr[index] != 1;
    }

    /**
     * 更新顶点 Dis
     *
     * @param index 出发顶点
     * @param len   距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;

    }

    /**
     * 更新前驱 Pre
     *
     * @param pre   pre 顶点下标
     * @param index 当前节点下标
     */
    public void updatePre(int pre, int index) {
        preVisited[pre] = index;
    }

    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 更新新的访问顶点
     * @return 下一个顶点
     */
    public int updateArr(){
        int index = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < alreadyArr.length; i++) {
            // 找未访问 && 距离最小的
            if(isVisited(i) && getDis(i) < min){
                index = i;
                min = dis[i];
            }
        }
        // 更新访问
        alreadyArr[index] = 1;
        return index;
    }
    /**
     * 显示最后结果
     */
    public void show(){
        System.out.println("=======================================");
        showArr(alreadyArr);
        showArr(preVisited);
        showArr(dis);

        for (int i = 0; i < dis.length; i++) {
            if(getDis(i) != Integer.MAX_VALUE){
                System.out.print(KruskalCase.CHARS[i] + "(" + getDis(i) + ")\t");
            }else {
                System.out.print("N\t");
            }
        }
        System.out.println();
    }

    private void showArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

}
