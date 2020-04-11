package com.github.howardli.algo.base;

/**
 *
 *  https://oj.leetcode.com/problems/longest-palindromic-substring/
 *
 * Question:
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there
 * exists one unique longest palindromic substring.
 *
 * Hint:
 * First, make sure you understand what a palindrome means.
 * A palindrome is a string which reads the same in both directions.
 * For example, “aba” is a palindome, “abc” is not.
 *
 * A common mistake:
 * Some people will be tempted to come up with a quick solution,
 * which is unfortunately flawed (however can be corrected easily):
 * Reverse S and become S’. Find the longest common substring between S and S’,
 * which must also be the longest palindromic substring.
 * This seemed to work, let’s see some examples below.
 * For example, S = “caba”, S’ = “abac”.
 * The longest common substring between S and S’ is “aba”, which is the answer.
 * Let’s try another example: S = “abacdfgdcaba”, S’ = “abacdgfdcaba”.
 * The longest common substring between S and S’ is “abacd”.
 * Clearly, this is not a valid palindrome.
 */
public class LongestPalindromicSubstr {

    public static void main(String[] args){
        System.out.println(solveV4(""));//""
        System.out.println(solveV4("caba"));//aba
        System.out.println(solveV4("abacdfgdcaba"));//aba
        System.out.println(solveV4("abacdffdcaba"));//abacdffdcaba
        System.out.println(solveV4("aacdefcaa")); //aa
        System.out.println(solveV4("babad")); //bab
        System.out.println(solveV4("abcda")); //a
    }

    /**
     * 每个回文有一个中心，最多有2n-1个中心
     * 遍历之
     *
     * 时间 - n2
     * 空间 - 1
     * @param s
     * @return
     */
    public static String solveV4(String s) {
        char[] cs = s.toCharArray();
        int maxLen = 0;
        int endIdx = -1;
        for(int i=0;i<cs.length;i++){
            int len1 = solveV4Sub(cs, i,i); // len1必为奇数
            if(len1>maxLen){
                maxLen = len1;
                endIdx = i+len1/2;
            }
            if(i+1<cs.length) {
                int len2 = solveV4Sub(cs, i, i + 1); //len2必为偶数
                if (len2 > maxLen) {
                    maxLen = len2;
                    endIdx = i + len2 / 2;
                }
            }
        }
        return String.valueOf(cs,endIdx+1-maxLen, maxLen);
    }

    private static int solveV4Sub(char[] cs, int l, int r) {
        while(l>=0&&r<cs.length&&cs[l]==cs[r]){l--;r++;}
        return r-l-1;
    }


    /**
     * p(i,j) - s(i,j)是回文
     * p(i,j) - p(i+1,j-1)是回文 and s(i)=s(j)
     * 时间 - n2
     * 空间 - n2
     * @param s
     * @return
     */
    public static String solveV3(String s) {
        char[] cs = s.toCharArray();
        int maxLen = 0;
        int endIdx = -1;
        // 初始化
        int[][] f = new int[cs.length][cs.length];
        for(int i=0;i<cs.length;i++){
            for(int j =i;j<cs.length;j++){
                if(i==0 || (cs[j-i]==cs[j]&&(i==1||f[j-i+1][j-1]==1))){
                    f[j-i][j]=1;
                    if(i+1>maxLen){
                        maxLen=i+1;
                        endIdx=j;
                    }
                }
            }
        }
        return String.valueOf(cs,endIdx+1-maxLen, maxLen);
    }

    /**
     * 整个字符串反转，动态规划求最大公共子串
     * 时间 - n2???
     * 空间 - n
     *
     * @param s
     * @return
     */
    public static String solveV2(String s){
        char[] cs = s.toCharArray();
        char[] re = new char[cs.length];
        for(int i=0;i<cs.length;i++) re[i]=cs[cs.length-1-i];
        int[][] f = new int[2][cs.length+1];
        int maxLen = 0;
        int endIdx = -1;
        for(int i=0;i<cs.length;i++){
            int f1SetIdx = i%2;
            int f1GetIdx = (i+1)%2;
            for(int j=0;j<cs.length;j++){
                if(cs[i]==re[j]){
                    f[f1SetIdx][j+1]=f[f1GetIdx][j]+1;
                    if(f[f1SetIdx][j+1]>maxLen){
                        // 检查回文
                        int l = i+1-f[f1SetIdx][j+1], r = i;
                        while(l<r&&cs[l]==cs[r]){l++;r--;}
                        if(l<r) continue;
                        maxLen = f[f1SetIdx][j+1];
                        endIdx = i;
                    }
                }else f[f1SetIdx][j+1]=0;

            }
        }
        return String.valueOf(cs, endIdx+1-maxLen, maxLen);
    }



    /**
     * 暴力检查每个可能的子串
     * 时间 - n3
     * 空间 - 1
     * 优化1 - 最大长度检查
     *
     * @param s
     * @return
     */
    public static String sloveV1(String s){
        char[] cs = s.toCharArray();
        if(s.length()==0) return "";
        int maxLen = 1;
        int startIdx = 0;
        for(int i=0;i<cs.length;i++){
            for(int j = i+1;j<cs.length;j++){
                if(j-i+1<=maxLen) continue; //优化1
                int l = i, r = j;
                while(l<r&&cs[l]==cs[r]){
                    l++;
                    r--;
                }
                if(r<=l&&j-i+1>maxLen){
                    maxLen = j-i+1;
                    startIdx = i;
                }
            }
        }
        return String.valueOf(cs, startIdx, maxLen);
    }

}
