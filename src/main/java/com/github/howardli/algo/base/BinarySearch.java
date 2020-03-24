package com.github.howardli.algo.base;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.function.ToIntFunction;

/**
 * 二分查找
 * 输入：有序数组A，数字a
 * 输出：a在A中的位置，不存在返回-1
 */
public class BinarySearch {
    static Random rand = new Random(System.currentTimeMillis());

    public static void main(String[] args){
        test();
    }

    public static void test(){
        int[] input = null;
        int b = 0;
        input = new int[]{0, 0,0,1,1,3};
        b = 1;
        int pos = solveNoRecursion(input,b);
        System.out.println(String.format("the pos of %d in %s is %d", b, Arrays.toString(input), pos));
    }

    public static void randomTest(){
        int bound = 10;
        int testCount = 5;
        for (int i = 0; i < testCount; i++) {
            Integer[] rawInput = randomArray(15, bound);
            if(rand.nextInt(bound)>(bound/2)){
                Arrays.sort(rawInput);
            }else{
                Arrays.sort(rawInput, Collections.reverseOrder());
            }
            int[] input = Arrays.stream(rawInput).mapToInt(new ToIntFunction<Integer>() {
                @Override
                public int applyAsInt(Integer value) {
                    return value;
                }
            }).toArray();
            int b = rand.nextInt(2*bound);
            int pos = solveRecursion(input,b);
            System.out.println(String.format("the pos of %d in %s is %d", b, Arrays.toString(input), pos));
        }

    }

    public static Integer[] randomArray(int size, int bound){
        Integer[] res = new Integer[size];
        for (int i = 0; i < size; i++) {
            res[i] = rand.nextInt(bound);
        }
        return res;
    }

    /**
     * 不重复，正序
     * @param nums
     * @param target
     * @return
     */
    public static int solveNoRecursionSimple(int[] nums, int target){
        int pivot, left = 0, right = nums.length-1;
        while(left<=right){
            pivot = (left + right)/2;
            if(nums[pivot]<target){
                left = pivot +1;
            }else if(nums[pivot]>target){
                right = pivot - 1;
            }else {
                return pivot;
            }
        }
        return -1;
    }

    public static int solveNoRecursion(int[] nums, int target){
        int pos = -1;
        int order = 1;
        int end = nums.length - 1;
        if(nums[0]==nums[end]){
            if(nums[0]==target){
                return 0;
            }else{
                return -1;
            }
        }else if (nums[0]>nums[end]){
            order = -1;
        }
        int start = 0;
        while(true){
            if(start>end){
                break;
            }else if(start==end){
                if(nums[start]==target){
                    pos = start;
                }
                break;
            }else{
                int midIndex = (start+end)/2;
                if(nums[midIndex]==target){
                    pos = midIndex;
                    break;
                }else{
                    int tmp = (target-nums[midIndex])*order;
                    if(tmp>0){
                        start = midIndex +1;
                    }else{
                        end = midIndex - 1;
                    }
                }
            }
        }
        return pos;
    }


    public static int solveRecursion(int[] nums, int target){
        if(nums==null || nums.length<=0){
            return -1;
        }
        int order = 1;
        int end = nums.length - 1;
        if(nums[0]==nums[end]){
            if(nums[0]==target){
                return 0;
            }else{
                return -1;
            }
        }else if (nums[0]>nums[end]){
            order = -1;
        }
        return solveRecursion(nums, target, 0, end, order);
    }



    /**
     * @param nums
     * @param target
     * @param start
     * @param end
     * @param order - 1升序，-1降序
     * @return
     */
    private static int solveRecursion(int[] nums, int target, int start, int end, int order){
        if(start>end){
            return -1;
        }else if (start==end){
            if(nums[start]==target){
                return start;
            }else{
                return -1;
            }
        }else{
            int midIndex = (start+end)/2;
            if(nums[midIndex]==target){
                return midIndex;
            }else {
                int tmp = (target-nums[midIndex])*order; // >0 or <0
                if(tmp>0){
                    return solveRecursion(nums, target, midIndex+1, end, order);
                }else{
                    return solveRecursion(nums, target, start, midIndex-1, order);
                }
            }
        }
    }
}
