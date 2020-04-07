package com.github.howardli.algo.base;

import java.util.*;

/**
 * Question:
 *
 * Given a string S, find the length of the longest substring T that contains at most two distinct characters.
 * For example,
 * Given S = “eceba”,
 * T is "ece" which its length is 3.
 */
public class LongestSubstr2 {
    public static void main(String[] args){
        System.out.println(solveV1_1("eceba", 2));//3
        System.out.println(solveV1_1("ababa", 2));//5
    }


    /**
     * 数组来记字符的出现的次数，不同字符数量n
     * 一次循环：
     * 1、如果没有出现，n++，如果n>2，则
     *
     * @param s
     * @param distinctCount
     * @return
     */
    public static int solveV3(String s, int distinctCount){
        return 0;
    }

    /**
     *
     * 不用map，用什么来记呢？
     *
     * 看两个字符，如果相同则继续，如果不同，之前还有没有第3个字符，如
     *
     *
     * 时间 - n
     * 空间 - 1
     * @param s
     * @param distinctCount
     * @return
     */
    public static int solveV2(String s, int distinctCount){
        int n = s.length();
        int l = 0;
        int maxLen = 0;
        int j = -1; // 上一个字符的位置
        for(int r =1;r<n;r++){
            if(s.charAt(r)==s.charAt(r-1)) continue;
            // l..j..r-1,r
            if(j>=0&&s.charAt(r)!=s.charAt(j)){
                maxLen = Math.max(r-l, maxLen);
                l = j+1;
            }
            j = r - 1;
        }
        return Math.max(n-l, maxLen);

    }

    /**
     * 用2位的map，来记录存在的字符和最大位置
     * 一次循环
     * 1、加入map，如果map长度大于2则处理一个潜在的最长子串
     *
     * 时间 - n
     * 空间 - 1
     * map部分有点丑，最大不同字符数改为3及以上呢？
     * @param s
     * @return
     */
    public static int solveV1(String s, int distinctCount){
        int n = s.length();
        int l = 0;
        int maxLen = 0;
        Map<Character, Integer> aa = new HashMap<>();
        for(int r =0;r<n;r++){
            char cur = s.charAt(r);
            if(aa.containsKey(cur)||aa.size()<distinctCount){
                aa.put(cur, r); // 插入坐标或者更新最大坐标
            }else{ // 第三个字符出现，一种最大子串的可能、l移动到两个最大坐标中较小的+1、清空aa中最大坐标较小的那个
                Map.Entry<Character, Integer> minEnt = null;
                for (Map.Entry<Character, Integer> ent : aa.entrySet())
                    if(minEnt==null||minEnt.getValue()>ent.getValue())minEnt=ent;
                maxLen = Math.max(r-l, maxLen);
                l = minEnt.getValue()+1;
                aa.remove(minEnt);
            }
        }
        // 如果一直没有出现第3个字符呢？l=0,
        return Math.max(n-l, maxLen);
    }

    /**
     * 时间 - n
     * 空间 - 1
     *
     * @param s
     * @return
     */
    public static int solveV1_1(String s, int distinctCount){
        int n = s.length();
        int l = 0; // 记录子串的起始位置
        Map<Character, Integer> c2MapIdx = new HashMap<>(); // 记录子串中每个字符出现的最大坐标
        int maxLen = 0; // 子串的最大长度
        for(int r =0;r<n;r++){
            char cur = s.charAt(r);
            c2MapIdx.put(cur, r); // 写入最新的坐标
            if(c2MapIdx.size()>distinctCount) { // 如果字符数超限，做2件事：子串重新起始位置、移除一个字符
                Map.Entry<Character, Integer> minEnt = null;
                for (Map.Entry<Character, Integer> ent : c2MapIdx.entrySet())
                    if (minEnt == null || minEnt.getValue() > ent.getValue()) minEnt = ent;
                l = minEnt.getValue() + 1; // 子串重新起始位置
                c2MapIdx.remove(minEnt);  // 移除一个字符(最大坐标最小的字符)
            }
            maxLen = Math.max(r - l + 1, maxLen);//每次都尝试计算最大长度，返回逻辑比较简单
        }
        return maxLen;
    }
}
