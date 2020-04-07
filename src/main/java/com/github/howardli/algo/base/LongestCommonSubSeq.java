package com.github.howardli.algo.base;

/**
 *
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 *
 * A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

 *
 * If there is no common subsequence, return 0.
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 */
public class LongestCommonSubSeq {
    public static void main(String[] args){
        System.out.println(solveV2RetLength("abc","def"));//""
        System.out.println(solveV2RetLength("abc","abc"));//"abc"
        System.out.println(solveV2RetLength("abcde","ace"));//"ace"
    }


    /**
     * 转成chararray，时间超过98%的用户
     * @param text1
     * @param text2
     * @return
     */
    public static int solveV2RetLength(String text1, String text2){
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();

        int[][] dp = new int[t1.length+1][t2.length+1];

        int max = 0;
        for (int x = 1; x<=t1.length; x++) {
            for (int y =1; y<=t2.length; y++) {
                if (t1[x-1] == t2[y-1]) {
                    dp[x][y] = 1 + dp[x-1][y-1];
                    max = Math.max(dp[x][y], max);
                } else {
                    dp[x][y] = Math.max(dp[x-1][y], dp[x][y-1]);
                }
            }
        }
        return max;
    }


    /**
     *
     * 时间 - mn
     * 空间 - mn
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int solveV1RetLength(String text1, String text2){
        int[][] f = new int[text1.length()+1][text2.length()+1];
        int maxLen = 0;
        for(int i=1;i<=text1.length();i++){
            for(int j=1;j<=text2.length();j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    f[i][j] = f[i-1][j-1]+1;
                    maxLen = Math.max(maxLen, f[i][j]);
                }else{
                    f[i][j] = Math.max(f[i-1][j], f[i][j-1]);
                }

            }
        }
        return maxLen;
    }
}
