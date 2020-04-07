package com.github.howardli.algo.base;

/**
 * https://oj.leetcode.com/problems/implement-strstr/
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class StrStr {
    public static void main(String[] args){
//        System.out.println(strStrV2("aaaa","aaab"));//-1
        System.out.println(strStrV1("xx","xbxx"));//-1
//        System.out.println(strStrV2("aaaaa","bba"));//-1
//        System.out.println(strStrV2("hello","ll"));//2
//        System.out.println(strStrV2("aaaaa",""));//0
    }


    /**
     * 暴力，简洁版
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStrV2(String haystack, String needle) {
        int charCompCount = 0;
        for(int i=0;;i++){
            for(int j=0;;j++){
                if(j==needle.length()) {
                    System.out.println(String.format("charCompCount %d", charCompCount));
                    return i;
                }
                if(i+j==haystack.length()) {
                    System.out.println(String.format("charCompCount %d", charCompCount));
                    return -1;
                }
                charCompCount++;
                if(haystack.charAt(i+j)!=needle.charAt(j))
                    break;
            }
        }

    }


    /**
     * 暴力
     * 时间 - mn
     * 空间 - 1
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStrV1(String haystack, String needle) {
//        int charCompCount = 0;
        int lenDif = haystack.length()- needle.length();
        for(int i=0;i<=lenDif;i++){
            boolean match = true;
            for(int j = 0;j<needle.length();j++){
//                charCompCount++;
                if(haystack.charAt(j+i)!=needle.charAt(j)){
                    match = false;
                    break;
                }
            }
            if(match) {
//                System.out.println(String.format("charCompCount %d", charCompCount));
                return i;
            }
        }
//        System.out.println(String.format("charCompCount %d", charCompCount));
        return -1;
    }
}
