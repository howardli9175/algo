package com.github.howardli.algo.base;

import java.util.*;

/**
 *
 * Code it now: https://oj.leetcode.com/problems/valid-parentheses/
 * Difficulty: Easy, Frequency: High
 * Question:
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid
 * but "(]" and "([)]" are not.
 * Example Questions Candidate Might Ask:
 * Q: Is the empty string valid?
 * A: Yes.
 *
 */
public class ValidParenthesis {
    public static void main(String[] args){
        System.out.println(solveV2(""));//true
        System.out.println(solveV2("()"));//true
        System.out.println(solveV2("()[]{}"));//true
        System.out.println(solveV2("(]"));//false
        System.out.println(solveV2("([)]"));//false
        System.out.println(solveV2("}"));//false
        System.out.println(solveV2("{{}"));//false
    }


    /**
     * 链表
     * 时间 - n
     * 空间 - n
     * leetcode 2ms
     * @param s
     * @return
     */
    public static boolean solveV2(String s){
        LinkedList<Character> a = new LinkedList();
        for(char c:s.toCharArray())a.add(c);
        Map<Character, Character> pairs = new HashMap();
        pairs.put('(',')');
        pairs.put('[',']');
        pairs.put('{','}');
        ListIterator<Character> it = a.listIterator();
        char l = 0;
        while(it.hasNext()){
            char ct = it.next();
            if(!pairs.containsKey(ct)){
                it.remove(); // 移除右括号
                if(it.hasPrevious()) {l = it.previous();it.remove();} // 如果有前面的元素，取出并移除
                if(!pairs.containsKey(l)||pairs.get(l)!=ct)return false;
            }
        }
        return a.size()==0;
    }

    /**
     * 栈
     * 时间 - n
     * 空间 - n
     * leetcode 1ms
     * @param s
     * @return
     */
    public static boolean solveV1(String s){
        char[] cs = s.toCharArray();
        Stack<Character> ls = new Stack();
        Map<Character, Character> pairs = new HashMap();
        pairs.put('(',')');
        pairs.put('[',']');
        pairs.put('{','}');
        for(Character c:cs){
            if(pairs.containsKey(c)) ls.push(c);
            else if(ls.isEmpty()||pairs.get(ls.pop())!=c) return false;
        }
        return ls.isEmpty();
    }
}
