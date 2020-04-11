package com.github.howardli.algo.base;

import java.util.Arrays;

/**
 * Code it now: https://oj.leetcode.com/problems/plus-one/
 * Difficulty: Easy, Frequency: High
 *
 * Question:
 * Given a number represented as an array of digits, plus one to the number.
 * Example Questions Candidate Might Ask:
 * Q: Could the number be negative?
 * A: No. Assume it is a non-negative number.
 * Q: How are the digits ordered in the list? For example, is the number 12 represented by [1,2] or [2,1]?
 * A: The digits are stored such that the most significant digit is at the head of the list.
 * Q: Could the number contain leading zeros, such as [0,0,1]?
 * A: No.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 */
public class PlusOne {
    public static void main(String[] args){
        System.out.println(Arrays.toString(solveV1(new int[]{1,2,3})));//1,2,4
        System.out.println(Arrays.toString(solveV1(new int[]{9,9,9})));//1,0,0,0
        System.out.println(Arrays.toString(solveV1(new int[]{0})));//1
        System.out.println(Arrays.toString(solveV1(new int[]{1,2,9})));//1,3,0
    }

    public static int[] solveV1(int[] digits){
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i]<9) {
                digits[i]+=1;
                return digits;
            }else{
                digits[i]=0;
            }
        }
        int[] res = new int[digits.length+1];
        res[0]= 1;
        System.arraycopy(digits, 0, res, 1, digits.length);
        return res;
    }
}
