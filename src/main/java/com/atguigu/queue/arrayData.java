package com.atguigu.queue;

import java.util.*;

/**
 * @author guyao
 * @create 2021-06-13 19:14
 */
public class arrayData {
    public static void main(String[] args) {
        //测试一把
        //创建队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
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

//使用数组模拟队列-编写一个ArrayQueue类
//缺点：不能复用；（利用%调整
class ArrayQueue {
    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据，模拟数列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部;分析出front是指向队列头的前一个位置
        rear = -1;//指向队列尾（包含具体数据
    }

    //判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;

    }

    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull())
            return;
        arr[++rear] = n;
    }

    //取数据
    public int getQueue() {
        if (isEmpty())//通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        return arr[++front];
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("空数据");
            return;
        }
        for (int i = front + 1; i <= rear; i++)
            System.out.printf("arr[%d] = %d\t", i, arr[i]);
        System.out.println();
        return;


    }

    //显示队列头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty())
            throw new RuntimeException("空队列");
        return arr[front + 1];
    }
}
class Solution65 {
    public static void main(String[] args) {
        System.out.println(new Solution65().isNumber("66"));
        HashMap hashMap = new HashMap();
        Collection values = hashMap.values();
        ArrayList<Integer> arr = new ArrayList<>(hashMap.values());
        arr.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }
    public boolean isNumber(String s) {
        boolean ans = true, occur = false;
        int n = s.length();
        for (int i=0; i<n; i++) {
            char ch = s.charAt(i);
            if (ch == '+' || ch == '-') {
                if (!(i < n-1 && ((s.charAt(i+1)-'0' >= 0 && s.charAt(i+1)-'0' <= 9) || s.charAt(i+1) == '.')))
                    return false;
            }
            else if (ch == '.') {
                if (!((i > 0 && s.charAt(i-1)-'0'>=0 && s.charAt(i-1)-'0'<=9) ||
                        (i < n-1 && s.charAt(i+1)-'0'>=0 && s.charAt(i+1)-'0'<=9)) || occur)
                    return false;
                occur = true;
            }
            else if (ch == 'e' || ch == 'E') {
                if (i == 0 || i == n-1)
                    return false;
                else {
                    for (int j=i+1; j<n; j++) {
                        char c = s.charAt(j);
                        if ((c == '+' || c == '-') && !(j == i+1 && j != n-1))
                            return false;
                        if (c == '.' || (c-'a'>=0 && c-'a'<=25) || (c-'A'>=0 && c-'A'<=25))
                            return false;
                    }
                    break;
                }
            }
            else if (ch-'0' >= 0 && ch-'0'<=9) {
                if (i < n-1 && (s.charAt(i+1) == '+' || s.charAt(i+1) == '-'))
                    return false;
            }
            else
                return false;
        }
        return ans;
    }
}