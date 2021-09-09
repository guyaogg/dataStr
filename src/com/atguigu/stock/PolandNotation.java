package com.atguigu.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author guyao
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 完成中缀表达式转后缀表达式
        // 1.1+((2+3)×4)-5 -> 1 2 3 + 4 * + 5 –
        // 因为直接对一个str操作不方便，因此先将1+((2+3)×4)-5 => 中缀的list
        // 2.1+((2+3)×4)-5 =》 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        String expression = "10+((2+3)*4)-5+65-941+561-(55*66+66)+6";
        List<String> InfixExpressionList = toInfixExpression(expression);
        // 3.将中缀表达式list =》后缀表达式list
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(InfixExpressionList);
        System.out.println(parseSuffixExpressionList);
        System.out.printf("expression的结果为：%d,实际结果为：%d",calculate(parseSuffixExpressionList),10+((2+3)*4)-5+65-941+561-(55*66+66)+6);


        // 先定义给逆波兰表达式
        // （3+4）*5-6 =>  3 4 + 5 * 6;
        // (30+4)×5-6 // 4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / +
        // 说明为了方便，使用空格隔开
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
//        // 1.先将suffixExpression 放到ArrayList中
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println(rpnList);
//        // 2.将ArrayList传给一个方法，遍历ArrayList配合栈，完成计算
//        int calculate = calculate(rpnList);
//        System.out.printf("计算结果：%s = %d,正在结果为：%d",suffixExpression, calculate, 4*5-8+60+8/2);


    }
    /**
     * 将中缀表达式list =》后缀表达式list
     */
    public static List<String> parseSuffixExpressionList(List<String> ls){
        if(ls.isEmpty()){
            throw new NullPointerException();
        }
        // 定义栈和 返回的list
        ArrayList<String> suffixList = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        String lK = "(";
        String rK = ")";
        // 遍历ls
        for (String item : ls) {
            // 如果是一个数就入栈
            if(item.matches(numFlag)){
                suffixList.add(item);
            }else if(item.equals(lK)){
                stack.push(item);

            }else if(item.equals(rK)){
                //")"则依次弹出s1栈顶的运算符，并压入s2，直到遇到"("为止，此时将这一对括号丢弃
                while(!lK.equals(stack.peek())){
                    if(stack.isEmpty()){
                        throw new RuntimeException("输入的小括号有误");
                    }
                    suffixList.add(stack.pop());
                }
                // 消除括号
                stack.pop();

            }else {
                // 操作符
                // s1为空，或栈顶为"("直接压栈
//                if(stack.isEmpty() || lK.equals(stack.peek()) ){
//                    stack.push(item);
//                }
//                // 若优先级比栈顶运算符高压入s1
//                if(Operation.getValue(stack.peek()) < Operation.getValue(item)){
//                    stack.push(item);
//                }
                // 当item优先级小于等于栈顶将s1栈顶的运算符弹出并压入s2中，再转到上面重写比较
                while(!(stack.isEmpty()|| lK.equals(stack.peek())) && Operation.getValue(stack.peek()) >= Operation.getValue(item)){
                    suffixList.add(stack.pop());
                }
                stack.push(item);
            }
        }
        // 将s1中剩余的运算符依次弹出压入s2
        while (!stack.isEmpty()){
            suffixList.add(stack.pop());
        }
        // 即可返回后缀表达式
        return suffixList;
    }


    /**
     * 将中缀表达式 1+((2+3)×4)-5 转成对应的list
     */
    public static List<String> toInfixExpression(String suffixExpression){
        if(suffixExpression == null || suffixExpression.length() == 0){
            throw new NullPointerException();
        }
       int len = suffixExpression.length();
        // 定义List
        List<String> ls = new ArrayList<>();
        // 这是一个指针，用于遍历中缀表达式字符串
        int i = 0;
        // 这是作拼接使用
        String str;
        // 遍历的元素
        char ch;
        do{
            ch = suffixExpression.charAt(i);
            // 如果ch是非数字，就需要加入到ls中
            char numBegin = '0';
            char numEnd = '9';
            if(ch < numBegin || ch > numEnd){
                ls.add(String.valueOf(ch));
                i++;
            }else {
                // 如果是数字拼接,考虑多位数
                // 先置空；
                str = String.valueOf(ch);
                while(++i < len && ((ch = suffixExpression.charAt(i)) >= numBegin && ch <= numEnd)){
                    // 拼接
                    str += ch;

                }
                ls.add(str);
            }


        }while (i < len);
        // 返回结果
        return ls;
    }

    /**
     * 将一个逆波兰表达式，依次将数据放入ArrayList
     */
    public static List<String> getListString(String suffixExpression) {
        // 将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    /**
     * 完成对逆波兰表达式的运算
     * <p>
     * 从左至右扫描，将3和4压入堆栈；
     * 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 将5入栈；
     * 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     * 将6入栈；
     * 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    static String numFlag = "\\d+";

    public static int calculate(List<String> ls) {
        // 创建栈（一个栈即可）
        Stack<String> stack = new Stack<>();
        // 遍历ls
        ls.forEach(item -> {
            // 匹配多位数
            if (item.matches(numFlag)) {
                // 直接入栈
                stack.push(item);
            } else {
                // 为运算符计算
                //pop出两个数计算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                // 存放结果
                int res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("符号不为+-*/");
                }
                // 把res入栈
                stack.push(String.valueOf(res));

            }

        });
        // 最后留着stack的数据就是结果
        return Integer.parseInt(stack.pop());
    }


}
/**
 * 编写一个类运算符优先级返回
 */
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    /**
     * 写一个方法，返回运算符优先级
     */
    public static int getValue(String opera){
        int res = 0;
        switch (opera){
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                throw new RuntimeException("不知道拿错了");


        }
        return res;
    }
}
