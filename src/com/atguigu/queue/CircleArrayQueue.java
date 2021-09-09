package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author guyao
 * @create 2021-06-14 15:56
 */

public class CircleArrayQueue {
    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据，模拟数列

    //创建队列的构造器
    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize + 1;
        arr = new int[maxSize];
//        front = 0;//指向队列头部;分析出front是指向队列头的前一个位置
//        rear = 0;//指向队列尾（包含具体数据
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;

    }

    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("满数据");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //取数据
    public int getQueue() {
        if (isEmpty())//通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        int k = front;
        front = (front + 1) % maxSize;
        return arr[k];
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("空数据");
            return;
        }
        for (int i = front; i < front + size(); i++)
            System.out.printf("arr[%d] = %d\t", i % maxSize, arr[i % maxSize]);
        System.out.println();
        return;


    }
    //求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("空队列");
        return arr[front];
    }
}
 class ArrayDataq {
    public static void main(String[] args) {
        //测试一把
        //创建队列
        CircleArrayQueue arrayQueue = new CircleArrayQueue(3);
        char key = ' ';//接收用户接入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):显示队列头部数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    try {
                        arrayQueue.addQueue(value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是:%d\n", res);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("取出的数据是:%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e'://退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出");

    }
}
