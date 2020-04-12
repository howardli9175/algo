package com.github.howardli.algo.base;

/**
 *
 * Code it now: https://oj.leetcode.com/problems/palindrome-number/
 * Difficulty: Easy, Frequency: Medium
 * Question:
 * Determine whether an integer is a palindrome.
 * Do this without extra space.
 *
 * Example Questions Candidate Might Ask:
 * Q: Does negative integer such as –1 qualify as a palindrome?
 * A: For the purpose of discussion here, we define negative integers as non-palindrome.
 *
 * Coud you solve it without converting the integer to a string?
 */
public class PalindromeNumber {
    public static void main(String[] args){
        System.out.println(solveV3(12321));//true
        System.out.println(solveV3(-1));//false
        System.out.println(solveV3(123));//false
        System.out.println(solveV3(0));//true
        System.out.println(solveV3(121));//true
        System.out.println(solveV3(1000000003));//false
    }

    /**
     * 33 - 32+1， 100001
     * 22 - 16+4+2，10110
     * 11 - 8+2+1，  1011
     * 如何取得最低位数字？模10
     * 如何取得最高位数字？上来先除10直到0
     * 得到每个数字之后？第一个和最后一个比较？
     * 12321
     * 10000
     * 100
     * 1
     * 123321
     * 100000
     * 1000
     * 10
     * 0
     * @param x
     * @return
     */
    public static boolean solveV3(int x){
        if(x<0) return false;
        int div = 1;
        while(x/div>=10) div*=10;
        while(div>1){
            if(x/div!=x%10) return false;
            x = (x%div)/10;
            div /=100;
        }
        return true;
    }

    /**
     * 转成字符串
     * @param x
     * @return
     */
    public static boolean solveV2(int x){
        char[] nums = String.valueOf(x).toCharArray();
        int l = 0, r = nums.length-1;
        while(l<r&&nums[l]==nums[r]){l++;r--;}
        return l>=r;
    }

    /**
     *
     * 数字反转，和原结果对比
     * @param x
     * @return
     */
    public static boolean solveV1(int x){
        if(x<0) return false;
        int y = 0, x1 = x;
        final int MAX_DIV = Integer.MAX_VALUE/10;
        while(x1!=0){
            if(y>MAX_DIV) return false;
            y = y * 10+x1%10;
            x1/=10;
        }
        return x==y;
    }
}
