package com.atguigu;

import org.junit.jupiter.api.Test;

/**
 * @author guyao
 * @Description
 * @create 2021-07-02 9:05
 */
public class TestD {
    @Test
    public void test0(){
        int[] ints = {10, 6, 8, 7, 7, 8, 6, 4, 1, 5, 132, 5, 42, 56, 321, 2, 5, 132, 15, 31, 51, 23, 2, 1, 4, 5, 2, 3, 5, 4, 6, 21, 231, 5, 66};
        QuickP(ints,0,ints.length - 1);

        System.out.println(maxIceCream(ints, 5));
    }
    public int maxIceCream(int[] costs, int coins) {
        QuickP(costs,0,costs.length - 1);
        int sum = 0;
        int i = 0;
        for(;i < costs.length;i++){
            sum += costs[i];
            if(coins < sum) break;

        }
        return i;

    }
    //自写快排
    void QuickP(int[] costs,int l,int r){
        int left = l,right = r,can = costs[l];
        while(left < right){
            while(left < right && can <= costs[right])right--;
            if(left < right){
                costs[left] = costs[right];
                costs[right] = can;
            }
            while(left < right && can >= costs[left])left++;
            if(left < right){
                costs[right] = costs[left];
                costs[left] = can;
            }
        }
        if(right > l)
            QuickP(costs,l,right);
        if(right + 1 < r)
            QuickP(costs,right + 1,r);
    }
}
