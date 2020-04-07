package com.github.howardli.algo.util;

import java.util.Random;

public class StringUtil {

    private static char[] CHARS = new char[]{'a','a','a','a','a','a','a'};
    private static int CHARS_LEN = CHARS.length;

    public static void main(String[] args){
        System.out.println(getRandomLowerLetterString(10));
    }

    public static String getRandomLowerLetterString(int len){
        char[] chars = new char[len];
        Random rand = new Random(System.currentTimeMillis());
        for(int i=0;i<len;i++){
            chars[i]=(char)(rand.nextInt(26)+97);
        }
        return new String(chars);
    }
}
