package com.github.howardli.algo.base;

/**
 * https://oj.leetcode.com/problems/reverse-integer/
 *
 * Difficulty: Easy, Frequency: High
 *
 * Question:
 * Reverse digits of an integer. For example: x = 123, return 321.
 *
 * Example Questions Candidate Might Ask:
 * Q: What about negative integers?
 * A: For input x = –123, you should return –321.
 * Q: What if the integer’s last digit is 0? For example, x = 10, 100, ...
 * A: Ignore the leading 0 digits of the reversed integer. 10 and 100 are both reversed as 1.
 * Q: What if the reversed integer overflows? For example, input x = 1000000003.
 * A: In this case, your function should return 0.
 */
public class ReverseInteger {
    public static void main(String[] args){
//        System.out.println(-123/10);
//        System.out.println(-12/10);
//        System.out.println(-1/10);
//        System.out.println(-123%10);
//        System.out.println(-12%10);
//        System.out.println(-1%10);
        System.out.println(solveV2(-123));// -321
        System.out.println(solveV2(10));// 1
        System.out.println(solveV2(100));// 1
        System.out.println(solveV2(123));// 321
        System.out.println(solveV2(1000000003));// 0
        System.out.println(solveV2(-1000000003));// 0
        System.out.println(solveV2(Integer.MAX_VALUE)); //0 , 2147483647, 1463847412
        System.out.println(solveV2(Integer.MIN_VALUE)); //0
    }


    public static int solveV2(int x){
        int  res = 0;
        int MAX_DIV = Integer.MAX_VALUE/10;
        while(x!=0){
            if(Math.abs(res)>MAX_DIV) return 0;
            res = res*10+x%10;
            x /= 10;
        }
        return res;
    }

    /**
     * @param x
     * @return
     */
    public static int solveV1(int x){
        boolean minus = false;
        if(x<0) minus = true;
        char[] cs = Integer.toString(Math.abs(x)).toCharArray();
        int l = 0, r = cs.length-1;
        char tmp = 0;
        while(l<r){
            tmp = cs[l];
            cs[l]=cs[r];
            cs[r]=tmp;
            l++;
            r--;
        }
        try {
            return Integer.valueOf((minus?"-":"")+String.valueOf(cs,0,cs.length));
        }catch (Exception e ){
            return 0;
        }
//        return x;
    }
}
