package com.github.howardli.algo.base;

/**
 * https://oj.leetcode.com/problems/string-to-integer-atoi/
 *
 * Implement atoi to convert a string to an integer.
 * The atoi function first discards as many whitespace characters as necessary until
 * the first non-whitespace character is found. Then, starting from this character,
 * takes an optional initial plus or minus sign followed by as many numerical digits
 * as possible, and interprets them as a numerical value.
 *
 * 第一个非数字及以后的字符，直接忽略
 * The string can contain additional characters after those that form the integral
 * number, which are ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid integral
 * number, or if no such sequence exists because either str is empty or it contains
 * only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned. If the correct
 * value is out of the range of representable values, the maximum integer value (2147483647)
 * or the minimum integer value (–2147483648) is returned.
 */
public class String2Integer {
    public static void main(String[] args){
        // 2147483647 Integer.MAX_VALUE
        String[] strs=new String[]{"0","fds","2147483647fdf","-2147483648","-147483648","2147483648"};
        for (String str : strs) {
            System.out.println(String.format("%20d",solveV2(str) ));//
        }
    }

    private static int solveV2(String str){
        // MAX - 2147483647
        // MAX_DIV_10 - 214748364
        // MIN - -2147483648
        final int MAX_DIV_10 = Integer.MAX_VALUE/10;
        int i=0, n = str.length();
        while(i<n&&Character.isWhitespace(str.charAt(i)))i++;
        int sign = 1;
        if(i<n && str.charAt(i)=='+')i++;
        if(i<n && str.charAt(i)=='-'){i++;sign=-1;}
        int num = 0;
        while(i<n&&Character.isDigit(str.charAt(i))){
            int digit = Character.getNumericValue(str.charAt(i));
            if(num>MAX_DIV_10||(num==MAX_DIV_10 && digit>=8)){// 如果是最小值，if条件命中后也会返回最小值
                return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            num = num * 10+digit;
            i++;
        }
        return sign * num;
    }

    /**
     *
     * 时间 - n2
     * @param str
     * @return
     */
    private static int solveV1(String str) {
        final int MAX_10_EXP = 9;
        int numCount = str.length();
        int idx = 0;
        boolean minus = false;
        if(numCount<=0)throw new NumberFormatException();
        if(str.charAt(0)=='-'){
            idx = 1;
            minus = true;
            numCount--;
            if(numCount<=0||numCount>MAX_10_EXP+1)throw new NumberFormatException();
        }
        int res = 0;
        for(;idx<str.length();idx++){
            int tmp = (int)str.charAt(idx)-48;
            if(tmp<0||tmp>9) throw new NumberFormatException(); // 当前位非数字
            int tmpVal = tenPow(tmp, numCount-1);
            if(minus){
                if(Integer.MIN_VALUE+tmpVal>res) throw new NumberFormatException();// 负数，减去本位后超最小限
                else res -= tmpVal;
            }else{
                if(Integer.MAX_VALUE-tmpVal<res) throw new NumberFormatException();// 正数，加上本位后超最大限
                else res += tmpVal;
            }
            numCount--;
        }
        return res;
    }

    /**
     * 时间 - exp
     * @param num
     * @param exp
     * @return
     */
    private static int tenPow(int num, int exp){
        if(exp==9&&num>2)throw new NumberFormatException();//
        if(num==0) return num;
        for(;exp>0;exp--){
            num = num * 10;
        }
        return num;
    }
}
