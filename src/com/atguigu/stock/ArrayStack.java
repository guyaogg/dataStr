package com.atguigu.stock;

/**定义一个数组栈
 * @author guyao
 * @create 2021-07-12 19:16
 */
public class ArrayStack {
    private int maxSize;//栈大小
    private int[] stack;//数组模拟栈，数据放在数组中
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }
    //栈满
    public boolean isFull(){
        return top >= maxSize - 1;
    }
    //栈空
    public boolean isEmpty(){
        return top < 0;
    }
    //入栈-push
    public void push(int value){
        if(isFull()){
            System.out.println("z栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈-pop,返回栈顶数据，同时top-1
    public int pop(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈顶数据peek
    public int peek(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空，没数据");
        }
        int value = stack[top];
        return value;
    }
    //显示栈的情况[遍历栈],遍历时，需从栈顶显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，无数据");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stock[%d]=%d\n",i,stack[i]);
        }
    }


}
