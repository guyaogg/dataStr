package com.atguigu.tree;

/**
 * @Description
 * @Author 顾遥
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 先需要new一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "卢俊义");
        HeroNode node3 = new HeroNode(3, "吴用");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        // 我们先手动创建二叉树，后面学习递归创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);

//        // 测试前序遍历 1 - 2 - 3 - 5 - 4
//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        // 测试中序遍历 2 - 1 - 5 - 3 - 4
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        // 测试后序遍历 2 - 5 - 4 - 3 - 1
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//        System.out.println("前序查找：");
//        System.out.println(binaryTree.preOrderSearch(5));
//        System.out.println("中序查找：");
//        System.out.println(binaryTree.infixOrderSearch(5));
//        System.out.println("后序查找：");
//        System.out.println(binaryTree.postOrderSearch(2));

        // 删除节点
        System.out.println("删除前：");
        binaryTree.preOrder();
//        binaryTree.delNode(5);
        binaryTree.delFeiNode(1);
        System.out.println("删除后：");
        binaryTree.preOrder();



    }
}
class BinaryTree{
    public static final String EMPTY_TIP = "当前二叉树为空,无法遍历";
    protected HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    public boolean isEmpty(){
        return root == null;
    }
    /**
     * 删除目标节点
     */
    public void delFeiNode(int target){
        if(isEmpty()){
            System.out.println("空树，不能删除...");
            return;
        }
        if(root.getNo() == target){
            if(root.getLeft() != null){
                root.getLeft().setRight(root.getRight());
                root = root.getLeft();
            }else {
                root = root.getRight();
            }
            return;
        }
        root.delFeiNode(target);
    }
    /**
     * 删除目标节点
     */
    public void delNode(int target){
        if(isEmpty()){
            System.out.println("空树，不能删除...");
            return;
        }
        if(root.getNo() == target){
            root = null;
            return;
        }
        root.delNode(target);
    }
    /**
     *     前序遍历
      */
    public void preOrder(){
        if(!isEmpty()){
            root.preOrder();
        }else {
            System.out.println(EMPTY_TIP);
        }
    }
    public void infixOrder(){
        if(!isEmpty()){
            root.infixOrder();
        }else {
            System.out.println(EMPTY_TIP);
        }
    }
    public void postOrder(){
        if(!isEmpty()){
            root.postOrder();
        }else {
            System.out.println(EMPTY_TIP);
        }
    }
    public HeroNode preOrderSearch(int target){
        if(isEmpty()){
            return null;
        }
        return root.preOrderSearch(target);
    }
    public HeroNode infixOrderSearch(int target){
        if(isEmpty()){
            return null;
        }
        return root.infixOrderSearch(target);
    }
    public HeroNode postOrderSearch(int target){
        if(isEmpty()){
            return null;
        }
        return root.postOrderSearch(target);
    }
}
/**
 创建HeroNode节点
 */
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    public HeroNode(int no,String name){
        this.name = name;
        this.no = no;

    }
    private HeroNode del(HeroNode node){
        HeroNode res;
        if(node.getLeft() != null){
            node.getLeft().setRight(node.getRight());
            res = node.getLeft();
        }else {
            res = node.getRight();
        }
        return res;
    }
    /**
     * 非递归删除节点
     * 叶子
     *   删除该节点
     * 非叶子节点
     *   删除该节点，单字节，子节点顶替，双子节点左子节点顶替
     */
    public void delFeiNode(int target){
        // 判断是否是子节点
        if(left != null && left.no == target){
            left = del(left);
            return;
        }
        if(right != null && right.no == target){
            right = del(right);
            return;
        }
        // 左子树递归删除
        if(left != null){
            left.delFeiNode(target);
        }
        // 右子树递归删除
        if(right != null){
            right.delFeiNode(target);
        }

    }
    /**
     * 递归删除节点
     * 叶子
     *   删除该节点
     * 非叶子节点
     *   删除该子树
     */
    public void delNode(int target){
        // 判断是否是子节点
        if(left != null && left.no == target){
            left = null;
            return;
        }
        if(right != null && right.no == target){
            right = null;
            return;
        }
        // 左子树递归删除
        if(left != null){
            left.delNode(target);
        }
        // 右子树递归删除
        if(right != null){
            right.delNode(target);
        }

    }

    /**
     * 前序遍历查找
     */
    public HeroNode preOrderSearch(int target){
        System.out.println("前");
        HeroNode res = null;
        if(no == target){
            return this;
        }
        if(left != null ){
            res =  left.preOrderSearch(target);
        }
        if(res != null){
            return res;
        }
        if(right != null){
            res =  right.preOrderSearch(target);
        }
        return res;
    }
    /**
     * 中序遍历查找
     */
    public HeroNode infixOrderSearch(int target){
        HeroNode res = null;

        if(left != null ){
            res =  left.infixOrderSearch(target);
        }
        if(res != null){
            return res;
        }
        System.out.println("mid");
        if(no == target){
            return this;
        }
        if(right != null){
            res =  right.infixOrderSearch(target);
        }
        return res;
    }
    /**
     * 后序遍历查找
     */
    public HeroNode postOrderSearch(int target){
        HeroNode res = null;

        if(left != null ){
            res =  left.postOrderSearch(target);
        }
        if(res != null){
            return res;
        }
        if(right != null){
            res =  right.postOrderSearch(target);
        }
        if(res != null){
            return res;
        }
        System.out.println("hou");

        if(no == target){
            return this;
        }
        return null;
    }
    /**
     * 编写前序遍历方法
     */
    public void preOrder(){
        // 先输出父节点
        System.out.println(this);
        // 向左子树前序遍历
        if(this.left != null){
            this.left.preOrder();
        }

        // 递归向右子树前序遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }
    /**
     * 编写中序遍历方法
     */
    public void infixOrder(){

        // 向左子树中序遍历
        if(this.left != null){
            this.left.infixOrder();
        }
        // 输出当前节点
        System.out.println(this);

        // 递归向右子树前序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    /**
     * 编写后序遍历方法
     */
    public void postOrder(){

        // 向左子树前序遍历
        if(this.left != null){
            this.left.postOrder();
        }
        // 递归向右子树前序遍历
        if(this.right != null){
            this.right.postOrder();
        }
        // 先输出父节点
        System.out.println(this);

    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
}