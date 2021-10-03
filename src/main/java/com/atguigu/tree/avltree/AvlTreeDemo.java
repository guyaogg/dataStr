package com.atguigu.tree.avltree;

import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

/**
 * @author 顾遥
 */
@Slf4j
public class AvlTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9,31,25,1,51,13,2,3,4};
        AvlTree avlTree = new AvlTree();

        for (int i : arr) {
            avlTree.add(new Node(i));
        }

        avlTree.infixOrder();
        log.debug("树的高度：{}", avlTree.getRoot().height());
        log.debug("左子树的高度：{}", avlTree.getRoot().left.height());
        log.debug("右子树的高度：{}", avlTree.getRoot().right.height());
        log.debug("右子树的节点：{}", avlTree.getRoot().right);
        avlTree.delNode(51);
        avlTree.delNode(31);
        avlTree.delNode(25);
        avlTree.delNode(13);
        avlTree.delNode(11);


        log.info("树的高度：{}", avlTree.getRoot().height());
        log.info("左子树的高度：{}", avlTree.getRoot().left.height());
        log.info("右子树的高度：{}", avlTree.getRoot().right.height());
        log.info("右子树的节点：{}", avlTree.getRoot().right);


    }
}

/**
 * 平衡二叉树
 */
class AvlTree {
    private final Logger log = LoggerFactory.getLogger(AvlTree.class);
    private Node root;

    public Node getRoot() {
        return root;
    }

    public Node search(int value) {
        if (root == null) {
            throw new NullPointerException();
        } else {
            return root.search(value);
        }
    }


    public Node searchParent(int value) {
        if (root == null) {
            throw new NullPointerException();
        } else {
            return root.searchParent(value);
        }
    }

    public void delNode(Node node) {
        delNode(node.value);
    }

    public void delNode(int value) {
        if (root == null) {
            throw new NullPointerException();
        } else {
            Node targetNode = search(value);
            if (root.right == null && root.left == null) {
                root = null;
                return;
            }
            Node parentNode = searchParent(value);
            boolean twoChildCondition = targetNode.left != null && targetNode.right != null;
            // 删除叶子节点情况
            if (targetNode.left == null && targetNode.right == null) {
                if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
            } else
                // 删除是根节点
                if (parentNode == null && targetNode.right == null) {
                    root = targetNode.left;
                } else
                    // 删除有两颗 子树情况
                    if (twoChildCondition || parentNode == null) {
                        targetNode.value = delRightTreeMin(targetNode);
                    } else { // 删除只有一颗 子树情况
                        if (targetNode.left != null) {
                            if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                                parentNode.left = targetNode.left;
                            } else {
                                parentNode.right = targetNode.left;
                            }
                        } else {
                            if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                                parentNode.left = targetNode.right;
                            } else {
                                parentNode.right = targetNode.right;
                            }
                        }
                    }

        }
        // 平衡二叉树
        root.balance();
    }

    public int delRightTreeMin(Node node) {
        Node target = node.right;
        // 循环查找左子节点,就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 删掉最小节点
        delNode(target);
        // 返回最小值
        return target.value;
    }


    /**
     * 添加方法
     *
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }

    }

    /**
     * 遍历方法（中序
     */
    public void infixOrder() {
        if (root == null) {
            log.info(() -> "root 为空");
        } else {
            root.infixOrder();
        }
    }

}


/**
 * 平衡二叉树节点
 */
class Node {
    private final Logger log = LoggerFactory.getLogger(Node.class);

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 左旋转
     */
    private void leftRotate() {
        // 新节点，以当前节点的值
        Node newNode = new Node(value);
        // 设置左子树
        newNode.left = left;
        // 设置右子树
        newNode.right = right.left;
        // 替换当前节点
        value = right.value;
        // 替换左子树
        left = newNode;
        // 替换右子树
        right = right.right;
    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        right = newNode;
        left = left.left;
    }

    /**
     * 左子树高度
     *
     * @return 高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 右子树高度
     *
     * @return 高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 返回该节点为根节点的高度
     *
     * @return 高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 查找节点
     *
     * @param value 根据值
     * @return 所要查找的节点
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (this.value < value && right != null) {
            return right.search(value);
        } else if (this.value > value && left != null) {
            return left.search(value);
        } else {
            throw new RuntimeException("该树没有该节点");
        }
    }

    /**
     * 查找节点的父节点
     *
     * @param value 查找节点
     * @return 要求的父节点
     */
    public Node searchParent(int value) {
        // 是否就是该节点
        boolean rParent = left != null && left.value == value;
        boolean lParent = right != null && right.value == value;
        if (rParent || lParent) {
            return this;
        } else {
            if (value < this.value && left != null) {
                return left.searchParent(value);
            } else if (value > this.value && right != null) {
                return right.searchParent(value);
            } else if (this.value == value) {
                return null;
            }
        }
        throw new RuntimeException("该节点不存在父节点");
    }

    public void remove(Node node) {

    }

    public void remove(int value) {

    }

    /**
     * 中序遍历方法
     */
    public void infixOrder() {
        if (left != null) {
            left.infixOrder();
        }
        System.out.println(this);
        if (right != null) {
            right.infixOrder();
        }
    }

    /**
     * 添加节点方法
     * 递归形式添加，满足二叉排序树要求
     */
    public void add(Node node) {
        if (node == null) {
            throw new NullPointerException();
        }
        // 判断传入节点的值，和 root 的关系
        if (node.value < value) {
            // 如果左子节点为空，直接挂到这个地方
            if (left == null) {
                left = node;
            } else {
                // 向左子节点递归添加
                left.add(node);
            }

        } else if (node.value > value) {
            if (right == null) {
                right = node;
            } else {
                right.add(node);
            }
        } else {
            log.info(() -> "不可以添加重复值");
        }
        // 平衡二叉树
        balance();


    }

    /**
     * 双旋转平衡
     */
    public void balance() {
        if (rightHeight() - leftHeight() > 1) {
            // 左低，左旋转
            if (right.rightHeight() < right.leftHeight()) {
                // 先对右子树进行右旋转
                right.rightRotate();

            }
            leftRotate();

        } else if (leftHeight() - rightHeight() > 1) {
            if (left.leftHeight() < left.rightHeight()) {
                // 先对左子树进行左旋转
                left.leftRotate();

            }
            rightRotate();

        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
