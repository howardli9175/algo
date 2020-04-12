package com.github.howardli.algo.base;

import java.util.*;

/**
 * Code it now: https://oj.leetcode.com/problems/evaluate-reverse-polish-notation/
 * Difficulty: Medium, Frequency: Low
 *
 * Question:
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /.
 * Each operand may be an integer or another expression. Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * Example Questions Candidate Might Ask:
 * Q: Is an empty array a valid input?
 * A: No.
 */
public class EvaluateNotation {
    public static void main(String[] args){
        System.out.println(solveV3(new String[]{"2", "1", "+", "3", "*"}));//9
        System.out.println(solveV3(new String[]{"4", "13", "5", "/", "+"}));//6
    }


    /**
     * 双向链表 - 迭代器遍历
     * leetcode - 10ms
     *
     * @param tokens
     * @return
     */
    public static int solveV3(String[] tokens){
        LinkedList<String> a = new LinkedList(Arrays.asList(tokens));
        String opers = "+-*/";
        ListIterator<String> it = a.listIterator();
        int oper1=0, oper2=0, res = 0;
        String tmp;
        while(it.hasNext()){
            tmp = it.next();
            if(opers.contains(tmp)){
                switch(tmp) {
                    case "+":res = oper1+oper2;break;
                    case "-":res = oper1-oper2;break;
                    case "*":res = oper1*oper2;break;
                    default:res = oper1/oper2;
                }
                if(it.hasPrevious()) {it.previous();it.remove();}
                if(it.hasPrevious()) {it.previous();it.remove();}
                if(it.hasPrevious()) {it.previous();it.remove();}
                it.add(String.valueOf(res)); // insert before implicit cursor
                if(it.hasPrevious()) {it.previous();}
                if(it.hasPrevious()) {it.previous();}
            }else{
                oper1 = oper2;
                oper2 = Integer.valueOf(tmp);
            }
        }
        return Integer.valueOf(a.get(0));

    }

    /**
     * 双向链表 - 位置遍历
     *
     * leetcode 516ms
     *
     * @param tokens
     * @return
     */
    public static int solveV2(String[] tokens){
        LinkedList<String> a = new LinkedList(Arrays.asList(tokens));
        int i =0;
        String opers = "+-*/";

        while(i+2<a.size()){
            if(!opers.contains(a.get(i))&&!opers.contains(a.get(i+1))&& opers.contains(a.get(i+2))){
                int res = 0;
                if("+".equals(a.get(i+2))) res = Integer.valueOf(a.get(i))+Integer.valueOf(a.get(i+1));
                else if("-".equals(a.get(i+2))) res = Integer.valueOf(a.get(i))-Integer.valueOf(a.get(i+1));
                else if("*".equals(a.get(i+2))) res = Integer.valueOf(a.get(i))*Integer.valueOf(a.get(i+1));
                else  res = Integer.valueOf(a.get(i))/Integer.valueOf(a.get(i+1));
                a.remove(i+2);
                a.remove(i+1);
                a.remove(i);
                a.add(i, String.valueOf(res));
                if(i>0)i--;
            }else i++;
        }
        return Integer.valueOf(a.get(0));

    }

    /**
     * 栈
     * 时间 - n
     * 空间 - n
     * leetcode 4ms
     * @param tokens
     * @return
     */
    public static int solveV1(String[] tokens){
        Stack<Integer> a = new Stack();
        String opers = "+-*/";
        int i1=0, i2=0;
        for(String t:tokens){
            if(opers.contains(t)){
                i1 = a.pop();
                i2 = a.pop();
                if("+".equals(t)) a.push(i2+i1);
                else if("-".equals(t)) a.push(i2-i1);
                else if("*".equals(t)) a.push(i2*i1);
                else  a.push(i2/i1);
            }else{
                a.push(Integer.valueOf(t));
            }
        }
        return a.peek();
    }
}
