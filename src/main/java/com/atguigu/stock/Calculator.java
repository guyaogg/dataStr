package com.atguigu.stock;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author guyao
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "17+2*6-4-8*7-9";
        // 创建两个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义相关变量
        // 用于扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        String keepNum = "";
        char ch = ' ';
        // 循环扫描
        while (index < expression.length()) {
            // 依次扫描expression的每个字符
            ch = expression.charAt(index++);
            // 是运算符
            if (operStack.isOper(ch)) {
                // 空符号栈
                if (!operStack.isEmpty()) {
                    //  优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 运算结果入数栈
                        numStack.push(res);
                        // 当前操作符入栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }

                } else {
                    // 如果为空直接入栈或者优先级小于
                    operStack.push(ch);

                }
            } else {

                // 如果是数直接数栈
//                numStack.push(ch - '0');
                // 处理多位数
                keepNum += ch;
                // 判断下一个字符是否是数字，如果是就继续扫描，如果是运算符就入栈
                if (index >= expression.length() || operStack.isOper(expression.charAt(index))){
//                    如果扫描到最后或者是操作符直接入栈
                    numStack.push(Integer.parseInt(keepNum));
//                    清空
                    keepNum = "";
                }

            }
        }
        // 计算符号栈为空
        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            // 运算结果入数栈
            numStack.push(res);
        }
        // 最后将数栈最后一个数pop出就是结果
        res = numStack.pop();
        System.out.printf("表达式：%s = %d,正确结果为：%d", expression, res, 17 + 2 * 6 - 4 - 8 * 7 - 9);


    }
}

class ArrayStack2 {
    private int maxSize;//栈大小
    private int[] stack;//数组模拟栈，数据放在数组中
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top >= maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top < 0;
    }

    //入栈-push
    public void push(int value) {
        if (isFull()) {
            System.out.println("z栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop,返回栈顶数据，同时top-1
    public int pop() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈顶数据peek
    public int peek() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没数据");
        }
        int value = stack[top];
        return value;
    }

    //显示栈的情况[遍历栈],遍历时，需从栈顶显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stock[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符的优先级，优先级使用数字表示，数字越大优先级越高
     */
    Map<Character, Integer> operMap;

    {
        // 目前只有+-*/
        operMap = new HashMap<>(4);
        operMap.put('*', 1);
        operMap.put('/', 1);
        operMap.put('-', 0);
        operMap.put('+', 0);
    }

    public int priority(int oper) {
        return operMap.getOrDefault((char) oper, -1);
    }

    /**
     * 判断是否是运算符
     */
    public boolean isOper(char oper) {
        return operMap.containsKey(oper);
    }

    /**
     * 计算方法
     */
    public int cal(int num1, int num2, int oper) {
        // res用于存放计算结果
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }


}
