package com.github.howardli.algo.base;

import java.util.*;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * add(input) – Add the number input to an internal data structure.
 * find(value) – Find if there exists any pair of numbers which sum is equal to the value.
 * For example,
 * add(1); add(3); add(5); find(4)􏴀true; find(7)􏴀false
 */
public class TwoSumStruct {

    public static void main(String[] args){
        int n = 9;
        char[] res = new char[n];
        Arrays.fill(res,'a');
        if(n%2!=0) res[0]='b';
        String a = String.copyValueOf(res);
        Solve s = new Solve3();
        s.add(1);
        s.add(3);
        s.add(5);
        System.out.println(s.find(4));
        System.out.println(s.find(7));
    }


    private interface Solve{
        void add(Integer i);
        boolean find(Integer target);
    }

    /**
     * 把所有可能和存下来
     * add - n
     * find - 1
     * 空间 - n2
     */
    private static class Solve3 implements Solve{

        private List<Integer> arr = new ArrayList<>();
        private Set<Integer> sums = new HashSet<>();

        @Override
        public void add(Integer i) {
            arr.add(i);
            for(int k=0;k<arr.size()-1;k++){
                sums.add(arr.get(k)+i);
            }
            System.out.println(String.format("arr %s, sums %s", arr, sums));
        }

        @Override
        public boolean find(Integer target) {
            return sums.contains(target);
        }
    }

    /**
     * 集合
     * add - 1
     * find - n
     * 空间 - n
     */
    private static class Solve2 implements Solve{

        private Set<Integer> arr = new HashSet<>();

        @Override
        public void add(Integer i) {
            arr.add(i);
        }

        @Override
        public boolean find(Integer target) {
            Iterator<Integer> iter = arr.iterator();
            while(iter.hasNext()){
                if(arr.contains(target-iter.next())){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 有序数组
     * add - 插入排序，时间n。插入可以通过二分查找插入位置，时间变为logn
     * find - 排序查找，时间n
     * 空间 - n
     */
    private static class Solve1 implements Solve{
        private List<Integer> arr = new ArrayList<>();
        public void add(Integer i){
            int toInsertIdx = arr.size();
            arr.add(i);
            while(toInsertIdx>0 && arr.get(toInsertIdx-1)>arr.get(toInsertIdx)){
                arr.set(toInsertIdx,arr.get(toInsertIdx-1));
                toInsertIdx--;
            }
            arr.set(toInsertIdx, i);
            System.out.println(arr);
        }

        public boolean find(Integer target){
            int left = 0, right = arr.size()-1;
            while(left<right){
                if(arr.get(left)+arr.get(right)==target){
                    return true;
                }else if (arr.get(left)+arr.get(right)<target){
                    left++;
                }else{
                    right--;
                }
            }
            return false;
        }
    }

}


