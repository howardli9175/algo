package com.github.howardli.algo.base;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Code it now: https://oj.leetcode.com/problems/single-number-ii/
 * Difficulty: Hard, Frequency: Medium Question:
 * Given an array of integers, every element appears three times except for one.
 * Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity.
 * Could you implement it without using extra memory?
 *
 */
public class SingleNumber2 {
    public static void main(String[] args){
        System.out.println(solveV3(new int[]{-2,-2,-3,-2}));//-3
        System.out.println(solveV3(new int[]{2,2,3,2}));//3
        System.out.println(solveV3(new int[]{1,0,0,0,1,1,99}));//99
    }

    /**
     * 掩码 todo 难懂，得画图
     * leetcode上0ms
     * @param nums
     * @return
     */
    public static int solveV3(int[] nums){
        int ones = 0, twos = 0, threes = 0;
        for(int i=0;i<nums.length;i++){
            // twos 包含：已2新1、已2新0
            // ones & nums[i] 包含：已1新1
            twos = twos | (ones & nums[i]); // 包含：已2新1、已2新0、已1新1
            ones = ones ^ nums[i]; // 包含：已2新1、已1新0、已0新1、已3新1
            threes = ones & twos; // 包含：已2新1
            ones = ones & (~threes); //
            twos = twos & (~threes);
        }
        return ones;
    }

    /**
     * & | ^ ~ << >> >>>
     * x&x=x
     * x&0=0
     * +x&MAX=+x
     * -x&MAX=???
     * x|x=x
     * x|0=x
     * x^x=0
     * x^0=x
     * x^-1=~x
     * 并没有什么用
     *
     * 每一位相加%3，必是single_num的那一位的值。
     * leetcode 3ms
     * @param nums
     * @return
     */
    public static int solveV2(int[] nums){
        int count = 0;
        int res = 0;
        for(int i=0;i<32;i++){
            count = 0;
            for(int j=0;j<nums.length;j++){
                if(((nums[j]>>i)&1)==1) count++;
            }
            res += (count%3)<<i; // 用加法
//            res |= (count[i]%3)<<i; //用或运算也可以, 或运算会快吗？todo
        }
        return res;
    }

    /**
     * Map
     * @param nums
     * @return
     */
    public static int solveV1(int[] nums){
        Map<Integer, Integer> tmp = new HashMap<>();
        for(int x:nums){
            if(tmp.containsKey(x)) tmp.put(x,tmp.get(x)+1);
            else tmp.put(x,1);
        }
        for(int x:nums){
            if(tmp.get(x)==1) return x;
        }
        throw new IllegalArgumentException("No single element");
    }
}
