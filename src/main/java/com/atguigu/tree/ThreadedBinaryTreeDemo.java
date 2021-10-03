package com.atguigu.tree;

/**
 * 线索化二叉树
 * @author 顾遥
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 测试一般中序线索化二叉树
        ThreadedHeroNode root = new ThreadedHeroNode(1, "汤姆");
        ThreadedHeroNode node2 = new ThreadedHeroNode(3, "jack");
        ThreadedHeroNode node3 = new ThreadedHeroNode(6, "smith");
        ThreadedHeroNode node4 = new ThreadedHeroNode(8, "mary");
        ThreadedHeroNode node5 = new ThreadedHeroNode(10, "king");
        ThreadedHeroNode node6 = new ThreadedHeroNode(14, "dim");

        // 二叉树，后面递归创建,目前手动创建
        root.setLeft(node2);
        root.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);

        // 测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        // 测试中序线索化
        threadedBinaryTree.infixThreadedNodes();
        // 测试:以十号节点测试
        ThreadedHeroNode leftNode = node5.getLeft();
        ThreadedHeroNode rightNode = node5.getRight();
        System.out.println("十号节点的前驱节点是 = " + leftNode + node5.getLeftType());
        System.out.println("十号节点的后继节点是 = " + rightNode);

        // 原先遍历(不能用了
//        threadedBinaryTree.infixOrder();
        // 改进中序遍历
       threadedBinaryTree.infixThreadedList();



    }
}
/**
 * 改进二叉树
 *
 */
class ThreadedBinaryTree extends BinaryTree{
    /**
     * 为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
     * 在递归实现线索化时，使用pre 保存前驱节点
     */
    private ThreadedHeroNode pre = null;
    public void infixThreadedNodes(){
        infixThreadedNodes((ThreadedHeroNode) root);
    }



    /**
     * 遍历线索化二叉树方法 ,中序
     */
    public void infixThreadedList(){
        // 定义一个变量，存储当前遍历的节点，从root开始
        ThreadedHeroNode node = (ThreadedHeroNode) root;
        while (node != null){
            // 循环找到leftType == 1的节点，第一个找到的就是8
            // 后面随着遍历而变化。因为leftType == 1，说明该节点是按照线索化
            // 处理后有效节点
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            // 打印当前节点
            System.out.println(node);
            // 如果当前节点的右节点指向的是后继节点，就一直输出
            while(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            // 右节点不是后继节点了,替代这个遍历节点
            node = node.getRight();
        }
    }
    /**
     * 中序线索化节点
     * @param node 需要线索化的节点
     */
    public void infixThreadedNodes(ThreadedHeroNode node){
        // 如果为空，就不能线索化
        if(node == null){
            return;
        }
        // 先线索化左子树
        infixThreadedNodes(node.getLeft());

        // 然后线索化当前节点[有难度]

        // 处理当前节点的前驱节点
        if(node.getLeft() == null){
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 指向前驱节点类型
            node.setLeftType(1);

        }
        // 处理pre后继节点
        if(pre != null && pre.getRight() == null){
            // 让前驱节点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        // 调整pre
        pre = node;
        // 最后线索化右子树
        infixThreadedNodes(node.getRight());


    }

}

/**
 * 创建改进 HeroNode
 */
class ThreadedHeroNode extends HeroNode {

    /**
     * 如果leftType == 0 表示指向左子树，1 前驱节点
     * 如果rightType == 0 表示指向右子树，1  后继节点
     */
    private int leftType;
    private int rightType;

    @Override
    public ThreadedHeroNode getLeft() {
        return (ThreadedHeroNode) super.getLeft();
    }
    @Override
    public ThreadedHeroNode getRight() {
        return (ThreadedHeroNode) super.getRight();
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public ThreadedHeroNode(int no, String name) {
        super(no, name);
    }
}