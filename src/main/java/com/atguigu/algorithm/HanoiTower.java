package com.atguigu.algorithm;

/**
 * 分治算法的汉诺塔
 *
 * @author 顾遥
 */
public class HanoiTower {
    public static void main(String[] args) {

        hanoiTower(1, 'A', 'B', 'C');
        // 超阈值了
        System.out.println(hanoiTower(64, true));

    }

    /**
     * 汉诺塔移动算法
     *
     * @param num 移动盘数
     * @param a   a 柱子
     * @param b   b 柱子
     * @param c   c 柱子
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num < 1) {
            throw new RuntimeException("移动盘不能小于1");
        }
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + " -> " + c);
        } else {
            // 把最上面移动  A -> B
            hanoiTower(num - 1, a, c, b);
            // 把最下面移动  A -> C
            System.out.println("第" + num + "个盘从 " + a + " -> " + c);
            // b 柱子移动   B -> C
            hanoiTower(num - 1, b, a, c);
        }
    }

    /**
     * 汉诺塔移动算法 返回次数
     * 其实就是 2 ^ n - 1
     *
     * @param num 移动盘数
     */
    public static long hanoiTower(int num) {
        if (num < 1) {
            throw new RuntimeException("移动盘不能小于1");
        }
        long ans = 0;
        // 如果只有一个盘
        if (num == 1) {
            ans++;
        } else {
            // 把最上面移动
            ans += hanoiTower(num - 1);
            // 把最下面移动
            ans++;
            // b 柱子移动
            ans += hanoiTower(num - 1);
        }
        return ans;
    }

    private static final int NUM_MAX_VALUE = 64;

    /**
     * 汉诺塔移动算法 返回次数
     * 其实就是 2 ^ n - 1
     *
     * @param num  移动盘数
     * @param flag true 使用简易方法
     */
    public static long hanoiTower(int num, boolean flag) {
        if (num > NUM_MAX_VALUE) {
            throw new RuntimeException("数值过大，请不要输入超过 " + NUM_MAX_VALUE + "的数");
        }
        if (flag) {
            long ans = 1;
            if (num == NUM_MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            while (num-- > 0) {
                ans *= 2;
            }
            return ans - 1;
        } else {
            return hanoiTower(num);
        }

    }


}
