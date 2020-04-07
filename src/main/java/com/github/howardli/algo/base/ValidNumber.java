package com.github.howardli.algo.base;

/**
 * https://oj.leetcode.com/problems/valid-number/
 *
 * Validate if a given string is numeric. Some examples:
 * "0" 􏴀 true "0.1" 􏴀 true "abc" 􏴀 false
 * Example Questions Candidate Might Ask:
 * Q: How to account for whitespaces in the string?
 * A: When deciding if a string is numeric, ignore both leading and trailing whitespaces.
 * Q: Should I ignore spaces in between numbers – such as “1 1”?
 * A: No, only ignore leading and trailing whitespaces. “1 1” is not numeric.
 * Q: If the string contains additional characters after a number, is it considered valid?
 * A: No. If the string contains any non-numeric characters (excluding whitespaces and decimal point), it is not numeric.
 * Q: Is it valid if a plus or minus sign appear before the number? A: Yes. “+1” and “-1” are both numeric.
 * Q: Should I consider only numbers in decimal? How about numbers in other bases such as hexadecimal (0xFF)?
 * A: Only consider decimal numbers. “0xFF” is not numeric.
 * Q: Should I consider exponent such as “1e10” as numeric?
 * A: No. But feel free to work on the challenge that takes exponent into consideration. (The Online Judge problem does take exponent into account.)
 *
 *
 * AeB
 * 前后空格忽略
 * 符号只能一个
 * A可正负，可小数，A必选，"e9" 不可以
 * B可正负，不可小数，B可选
 *
 * ".-4" 不可以
 *
 * 小数部分完整性？？？1./.1。 "3."是允许的，".1"是允许的，"."不允许
 * 整数部分多余0？？？0001
 * 小数部分多余0？？？1.000
 *
 */
public class ValidNumber {

    public static void main(String[] args){
        System.out.println(solveV1(".1"));//true
        System.out.println(solveV1("3."));//true
        System.out.println(solveV1("."));//false
        System.out.println(solveV1(".-4"));//false
        System.out.println(solveV1("e9"));//false
        System.out.println(solveV1("0"));//true
        System.out.println(solveV1("0.1"));//true
        System.out.println(solveV1("abc"));//false
        System.out.println(solveV1("1 a"));//false
        System.out.println(solveV1("-+3"));//false
        System.out.println(solveV1(" --6 "));//false
        System.out.println(solveV1(" 1e"));//false
        System.out.println(solveV1(" 99e2.5 "));//false
        System.out.println(solveV1("95a54e53"));//false
        System.out.println(solveV1("2e10"));//true
        System.out.println(solveV1(" -90e3      "));//true
        System.out.println(solveV1(" 6e-1"));//true
        System.out.println(solveV1("53.5e93"));//true
    }

    /**
     * 跳过空格
     * 跳过符号
     * 跳过数字
     * 如果遇到小数点，跳过数字
     * 如果遇到e，跳过数字
     *
     * @param s
     * @return
     */
    public static boolean solveV2(String s){
        int i = 0, n = s.length();
        while (i < n && Character.isWhitespace(s.charAt(i))) i++;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
        boolean isNumeric = false;
        while (i < n && Character.isDigit(s.charAt(i))) {
            i++;
            isNumeric = true;
        }
        if (i < n && s.charAt(i) == '.') {
            i++;
            while (i < n && Character.isDigit(s.charAt(i))) {
                i++;
                isNumeric = true;
            }
        }
        if (i < n && isNumeric && s.charAt(i) == 'e') {
            i++;
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;
            isNumeric = false;
            while (i<n && Character.isDigit(s.charAt(i))) {
                i++;
                isNumeric = true;
            }
        }
        while (i < n && Character.isWhitespace(s.charAt(i))) i++;
        return isNumeric && i == n;
    }

    public static boolean solveV1(String s){
        boolean sign = false; // 符号部分已确定
        boolean needNumNext = true; // 下一个需要有数字出现
        boolean frac = false; // 非指数部分有小数点
        boolean exp = false; // 有指数部分
        boolean expSign = false; // 指数有符号
        int i = 0, n = s.length(), j=n-1;
        while(i<n&&Character.isWhitespace(s.charAt(i)))i++;
        while(j>=i&&Character.isWhitespace(s.charAt(j)))j--;
        while(i<=j){
            char c = s.charAt(i);
            if(!exp){ // 可以出现数字、符号、小数点、e
                if(Character.isDigit(c)){
                    needNumNext = false;
                    sign = true;
                }else if (!sign  && (c=='+'||c=='-')){ //没确定符号，就可以出现符号
                    sign = true;
                    needNumNext = true;
                }else if (!frac && c=='.'){ // 没出过小数点，就可以出现小数点，但不能再出现符号了
                    frac = true;
                    sign = true;
                }else if (!needNumNext&&c=='e'){ // 已经出现过数字，就允许出现指数
                    exp = true;
                    needNumNext = true;
                }else{
                    return false;
                }
            }else{ // 可以出现符号、数字
                if(Character.isDigit(c)){
                    needNumNext = false;
                    expSign = true;
                }else if(!expSign&&(c=='+'||c=='-')){
                    expSign = true;
                    needNumNext = true;
                }else {
                    return false;
                }
            }
            i++;
        }
        return !needNumNext;
    }

}
