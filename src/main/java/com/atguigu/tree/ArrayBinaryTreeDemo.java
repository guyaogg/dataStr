package com.atguigu.tree;


/**
 *
 * @author 顾遥
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
//        int len = 7;
//        int[] arr = new int[len];
//        for (int i = 0; i < arr.length; ) {
//            arr[i++] = i;
//        }
        int[] arr = {1,3,6,8,10,14};
        // 创建顺序二叉树

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);

        arrayBinaryTree.preOrder();
        System.out.println();
        arrayBinaryTree.infixOrder();
        System.out.println();

        arrayBinaryTree.postOrder();

    }


}

/**
 * 编写 ArrayBinaryTree 实现顺序二叉树
 */
class ArrayBinaryTree {
    /**
     * 存储数据节点的数组
     */
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;

    }
    public boolean isEmpty(){
        return arr == null || arr.length == 0;
    }
    private int getLeft(int index){
        return index * 2 + 1;
    }
    private int getRight(int index){
        return index * 2 + 2;
    }
    /**
     * 重载 preOrder
     */
    public void preOrder(){
        preOrder(0);
    }
    /**
     * 编写方法完成前序遍历
     * @param index 当前数组下标
     */
    public void preOrder(int index){
        if(isEmpty()){
            System.out.println("当前数组为空，请设置二叉树");
        }
        // 输出当前这个元素
        System.out.print(arr[index] + "\t");
        // 向左边递归
        int left = getLeft(index);
        if(left < arr.length){
            preOrder(left);
        }
        int right = getRight(index);
        if(right < arr.length){
            preOrder(right);
        }



    }
    public  void postOrder(){
        postOrder(0);
    }
    /**
     * 后序遍历
     */
    public void postOrder(int index){
        if(isEmpty()){
            System.out.println("当前数组为空，请设置二叉树");
        }

        // 向左边递归
        int left = getLeft(index);
        if(left < arr.length){
            postOrder(left);
        }
        int right = getRight(index);
        if(right < arr.length){
            postOrder(right);
        }
        // 输出当前这个元素
        System.out.print(arr[index] + "\t");



    }
    public  void infixOrder(){
        infixOrder(0);
    }
    /**
     * 后序遍历
     */
    public void infixOrder(int index){
        if(isEmpty()){
            System.out.println("当前数组为空，请设置二叉树");
        }

        // 向左边递归
        int left = getLeft(index);
        if(left < arr.length){
            infixOrder(left);
        }
        // 输出当前这个元素
        System.out.print(arr[index] + "\t");
        int right = getRight(index);
        if(right < arr.length){
            infixOrder(right);
        }




    }


}
