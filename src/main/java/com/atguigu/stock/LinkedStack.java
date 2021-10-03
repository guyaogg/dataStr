package com.atguigu.stock;

/**用链表实现栈
 * @author guyao
 * @create 2021-07-12 19:42
 */
public class LinkedStack {
    private Node nodeStack;



    //栈空
    public boolean isEmpty(){
        return nodeStack == null;
    }
    //入栈-push
    public void push(int value){
        Node node = new Node();
        node.val = value;
        node.next = nodeStack;
        nodeStack = node;

    }
    //出栈-pop,返回栈顶数据，同时top-1
    public int pop(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没数据");
        }
        int value = nodeStack.val;
        nodeStack = nodeStack.next;
        return value;
    }
    //显示栈顶数据peek
    public int peek(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没数据");
        }
        int value = nodeStack.val;
        return value;
    }
    //显示栈的情况[遍历栈],遍历时，需从栈顶显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，无数据");
            return;
        }
        Node temp = nodeStack;
        while(temp != null){
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

}
class Node {
    Node next;
    int val;

}
