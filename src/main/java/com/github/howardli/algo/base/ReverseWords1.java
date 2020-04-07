package com.github.howardli.algo.base;

/**
 * https://oj.leetcode.com/problems/reverse-words-in-a-string/
 *
 * Question:
 * Given an input string s, reverse the string word by word.
 * For example, given s = "the sky is blue", return "blue is sky the".
 * Example Questions Candidate Might Ask:
 * Q: What constitutes a word?
 * A: A sequence of non-space characters constitutes a word.
 * Q: Does tab or newline character count as space characters?
 * A: Assume the input does not contain any tabs or newline characters.
 * Q: Could the input string contain leading or trailing spaces?
 * A: Yes. However, your reversed string should not contain leading or trailing spaces.
 * Q: How about multiple spaces between two words?
 * A: Reduce them to a single space in the reversed string.
 */
public class ReverseWords1 {
    public static void main(String[] args){
        String input = "the sky  is blue";
//        System.out.println(solveV1RetLength(input));//"blue is sky the"
        input = "   a good   example  ";
        System.out.println(String.format("#%s#", solveV3(input)));//"example good a"
    }


    private static String solveV3(String s){
        char[] chars = s.toCharArray();
        solveV3Sub(chars, 0, chars.length-1);
        int l = 0;
        for(int i=0;i<chars.length;i++){
            if(chars[i]==' ') l = i;
            else if(i==chars.length-1||chars[i+1]==' '){
                solveV3Sub(chars,l+1, i);
            }
        }
        return String.copyValueOf(chars);
    }

    private static void solveV3Sub(char[] chars, int l, int r){
        while(l<r){
            char tmp = chars[l];
            chars[l] = chars[r];
            chars[r] = tmp;
            l++;
            r--;
        }
    }

    /**
     *
     * 看当前位置和下一个位置
     *
     * 时间 - n
     * 空间 - n
     * @param s
     * @return
     */
    private static String solveV2(String s) {
        StringBuilder reversed = new StringBuilder();
        // i来标示单词beginIndex, end标示单词endIndex，end一定在一个空格处
        int end = s.length();
        // 每次看一下当前和前一格
        for(int i=s.length()-1;i>=00;i--){
            // 如果i是空格, end移动到i处
            if(s.charAt(i)==' ') {
                end = i;
            }
            // 如果i不是空格，i=0或者i-1是空格, i到end
            else if(i==0||s.charAt(i-1)==' '){
                if(reversed.length()>0){
                    reversed.append(" ");
                }
                reversed.append(s.substring(i,end));
            }
        }
        return reversed.toString();
    }

    /**
     * wrong？ why
     * 看当前位置
     * 找到空格 - substr
     * 找到非空格 - 确定起始位转置
     *
     * 时间 - n
     * 空间 - n
     * @param s
     * @return
     */
    private static String solveV1(String s) {
        StringBuilder reversed = new StringBuilder();
        int end = s.length(), start = end;
        for(int i=s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            if(start>=end) {
                if (c != ' ') {
                    end = i + 1;
                    start = i;
                }
            } else{
                if(c!=' '&&i>0){
                    start--;
                }else{
                    if(reversed.length()>0){
                        reversed.append(" ");
                    }
                    if(i==0) start=0;
                    reversed.append(s.substring(start,end));
                    end = start;
                }
            }
        }
        return reversed.toString();
    }
}
