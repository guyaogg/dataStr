package com.atguigu.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 顾遥
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);

        preOrder(huffmanTree);


    }

    public static void preOrder(Node root) {
        if (root != null) {
            System.out.println("是空树，无法遍历~~~");
        } else {
            root.preOrder();
        }
    }

    private static final int MIN_HUFF_LEN = 1;

    /**
     * 创建赫夫曼树
     *
     * @param arr 要转换的数组
     * @return 转化后的赫夫曼树
     */
    public static Node createHuffmanTree(int[] arr) {
        if (arr == null || arr.length < MIN_HUFF_LEN) {
            throw new NullPointerException();
        }
        // 第一步为了方便操作
        // 1.遍历 arr 数组
        // 2.将 arr 的每一个元素给成一个Node
        // 3.将 Node 放入ArrayList 中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        int i = 0;
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            i++;

            System.out.println("node = " + nodes);

            // 取出根节点最小的二叉树
            // (1) 取出权值两小的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            // (2 构建新的二叉树
            Node parent = new Node(leftNode.weight + rightNode.weight);

            parent.left = leftNode;
            parent.right = rightNode;

            // (在 ArrayList 中处理过的二叉树
            nodes.remove(0);
            nodes.remove(0);

            nodes.add(parent);
            System.out.println("第" + i + "次处理后：" + nodes);
        }

        // 返回最后节点
        return nodes.get(0);


    }
}

/**
 * 创建节点类
 * 支持排序
 */
class Node<T> implements Comparable<Node> {
    /**
     * 节点权值
     */
    int weight;
    /**
     * 节点数据
     */
    T data;
    Node left;
    Node right;

    public Node(int weight) {
        this.weight = weight;
    }

    public Node(int weight, T data) {
        this.weight = weight;
        this.data = data;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return Integer.compare(this.weight, o.weight);
    }

}
