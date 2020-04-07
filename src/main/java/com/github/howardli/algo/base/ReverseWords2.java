package com.github.howardli.algo.base;

/**
 * Similar to Question [6. Reverse Words in a String], but with the following constraints:
 * “The input string does not contain leading or trailing spaces and the words are always separated by a single space.”
 * Could you do it in-place without allocating extra space?
 */
public class ReverseWords2 {

    public static void main(String[] args){
        String s = "the sky is blue";
        System.out.println(solveV1(s.toCharArray()));// bule is sky the
    }

    private static String solveV1(char[] s) {
        // 整个数据组反转
        solveV1Sub(s, 0, s.length-1);
        // 每个单词反转
        int l = 0;
        for(int r=0;r<s.length;r++){
            // 到最后或者下一个是空格，反转单词，l移到r+2
            if(r==s.length-1||s[r+1]==' '){
                solveV1Sub(s, l, r);
                l = r+2;
            }
        }
        return new String(s);
    }

    private static void solveV1Sub(char[]s , int l, int r){
        while(l<r){
            char tmp = s[l];
            s[l++]=s[r];
            s[r--]=tmp;
        }
    }


}
