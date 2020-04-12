package com.github.howardli.algo.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * Code it now: https://oj.leetcode.com/problems/min-stack/
 *
 * Difficulty: Easy, Frequency: N/A
 * Question:
 * Design a stack that supports push, pop, top,
 *
 * and retrieving the minimum element in constant time.
 *
 * 􏰁 push(x) – Push element x onto stack.
 * 􏰁 pop() – Removes the element on top of the stack.
 * 􏰁 top() – Get the top element.
 * 􏰁 getMin() – Retrieve the minimum element in the stack.
 * Hints:
 * 􏰂 Consider space-time tradeoff.
 *  How would you keep track of the minimums using extra space?
 * 􏰂 Make sure to consider duplicate elements.
 *
 */
public class MinStack {
    public static void main(String[] args){

    }

    interface Solve{
        void push(int x);
        void pop();
        int top();
        int getMin();
    }

    /**
     * space - n
     * push - 1
     * pop - 1
     * top - 1
     * getMin - 1
     * time - 4ms
     * 双栈 - 最小栈去重
     */
    public static class SolveV3 implements Solve{

        List<Integer> eles;
        List<Integer> mins;

        public SolveV3(){
            eles = new ArrayList<>();
            mins = new ArrayList<>();
        }

        /**
         * 新加大于最小，最小栈不变
         * 新加等于最小，最小栈压栈
         * 新加小于最小，最小栈压栈
         * @param x
         */
        public void push(int x) {
            eles.add(x);
            mins.add(x);
            if(mins.size()>1&&mins.get(mins.size()-1)>mins.get(mins.size()-2)){
                mins.remove(mins.size()-1);
            }
        }

        /**
         * 弹出元素小于等于最小栈栈顶的时候，最小栈出栈
         */
        public void pop() {
            int i = eles.remove(eles.size()-1);
            if(i<=mins.get(mins.size()-1))
                mins.remove(mins.size()-1);
        }

        public int top() {
            return eles.get(eles.size()-1);
        }

        public int getMin() {
            return mins.get(mins.size()-1);
        }
    }

    /**
     * space - n
     * push - 1
     * pop - 1
     * top - 1
     * getMin - 1
     * time - 4ms
     *
     * 常数时间内，找到最小元素 - 存下来
     * 怎么存 - 存当前值的话，在pop的时候，新的最小值又需要遍历，节省空间但pop的时间为n
     * 怎么存 - 存数组，多一个n的空间，但所有操作都可以是1.
     * 怎么存 - 存数组，如何节省空间？
     * 不存最小值 - 可以吗？
     */
    public static class SolveV2 implements Solve{

        List<Integer> eles;
        List<Integer> mins;
        Stack a;

        public SolveV2(){
            eles = new ArrayList<>();
            mins = new ArrayList<>();
        }

        public void push(int x) {
            eles.add(x);
            mins.add(x);
            if(mins.size()>1&&mins.get(mins.size()-1)>mins.get(mins.size()-2)){
                mins.set(mins.size()-1,mins.get(mins.size()-2));
            }
        }

        public void pop() {
            eles.remove(eles.size()-1);
            mins.remove(mins.size()-1);
        }

        public int top() {
            return eles.get(eles.size()-1);
        }

        public int getMin() {
            return mins.get(mins.size()-1);
        }
    }

    /**
     * space - n
     * push - 1
     * pop - 1
     * top - 1
     * getMin - n
     * time - 344ms
     */
    public static class SolveV1 implements Solve{

        List<Integer> eles = new ArrayList<>();

        public void push(int x) {
            eles.add(x);
        }

        public void pop() {
            eles.remove(eles.size()-1);
        }

        public int top() {
            return eles.get(eles.size()-1);
        }

        public int getMin() {
            int min = eles.get(0);
            for(int i=1;i<eles.size();i++){
                if(eles.get(i)<min) min = eles.get(i);
            }
            return min;
        }
    }

}
