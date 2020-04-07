package com.github.howardli.algo.base;

/**
 * https://www.lintcode.com/problem/a-b-problem/description
 *
 * https://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/72993
 *
 */
public class APlusB {
    public static void main(String[] args){
        int a = 1100000000, b = 1204060000;
        System.out.println(solveV1(a,b));
        System.out.println(a+b);
    }

    /**
     *
     * 不用加减法实现加法，类似数字电路中的全加器，异或求得部分和，相与求得进位，
     * 最后将进位作为加法器的输入，典型的递归实现思路。
     * @param a
     * @param b
     * @return
     */
    public static int solveV1(int a, int b){
        // 3 + 6,
        // 0011
        // 0110
        System.out.println(String.format("%10s - %32s","a", Integer.toBinaryString(a)));
        System.out.println(String.format("%10s - %32s","b", Integer.toBinaryString(b)));
        int result = a ^ b; // 异或 0101 第1、3位有值
        System.out.println(String.format("%10s - %32s","a ^ b", Integer.toBinaryString(result)));
        int carry = a & b; // 与 0010 第2位有进位
        System.out.println(String.format("%10s - %31s","a & b", Integer.toBinaryString(carry)));
        carry <<= 1; //  左移一位，0100,
        System.out.println(String.format("%10s - %32s","a & b << 1", Integer.toBinaryString(carry)));
        System.out.println();
        if (carry != 0) {
            result = solveV1(result, carry); // 每递归一次carry右侧的0个数都会增加，增加到32位，递归结束
        }

        return result;
    }
}
