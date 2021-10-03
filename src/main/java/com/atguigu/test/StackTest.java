package com.atguigu.test;

import com.atguigu.stock.ArrayStack;
import com.atguigu.stock.LinkedStack;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * @author guyao
 * @create 2021-07-12 19:29
 */
public class StackTest {
    @Test
    public void test1() {
        //测试ArrayStock是否正确
        //create
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:表示程序");
            System.out.println("push:表示入栈");
            System.out.println("pop:表示出栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个值");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.printf("出栈数据是：%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("退出程序");
    }
    @Test
    public void test2() {
        //测试LinkedStack是否正确
        //create
        LinkedStack arrayStack = new LinkedStack();
        String key = "";
        boolean loop = true;//控制是否退出
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:表示程序");
            System.out.println("push:表示入栈");
            System.out.println("pop:表示出栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    arrayStack.list();
                    break;
                case "push":
                    System.out.println("请输入一个值");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.printf("出栈数据是：%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("退出程序");
    }

}
