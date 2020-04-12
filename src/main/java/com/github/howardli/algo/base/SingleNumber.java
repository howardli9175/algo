package com.github.howardli.algo.base;

/**
 * Code it now: https://oj.leetcode.com/problems/single-number/
 * Difficulty: Medium, Frequency: High
 *
 * Question:
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Example Questions Candidate Might Ask:
 * Q: Does the array contain both positive and negative integers?
 * A: Yes.
 * Q: Could any element appear more than twice?
 * A: No.
 */
public class SingleNumber {
    public static void main(String[] args){
        System.out.println(solveV1(new int[]{1,1,2})); // 2
        System.out.println(solveV1(new int[]{1,1,-2})); // -2
    }

    /**
     * 异或
     * x^x=0
     * x^y=y^x
     * (x^y)^z=x^(y^z)
     *
     * x^y^z^x = x^y^x^z = x^x^y^z
     *
     * @param nums
     * @return
     */
    public static int solveV1(int[] nums){
        int res= 0;
        for(int x: nums)
            res ^= x; // & | ^ ~ << >> >>>
        return res;
    }
}
