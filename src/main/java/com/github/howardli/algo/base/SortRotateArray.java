package com.github.howardli.algo.base;

/**
 *
 * Code it now: https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * Difficulty: Medium, Frequency: High
 * Question:
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 *
 */
public class SortRotateArray {
    public static void main(String[] args){
        System.out.println(solveV2(new int[]{4,5,6,7,0,1,2}));//0
        System.out.println(solveV2(new int[]{3,4,5,1,2}));//1
        System.out.println(solveV2(new int[]{1,2,3,4,5}));//1
    }

    /**
     *
     * 二分查找
     * 时间 - logn
     * 4 5 6 7 0 1 2
     * 5 6 7 0 1 2 4
     * 1 2 4 5 6 7 0
     * 7 0 1 2 4 5 6
     * 0 1 2 4 5 6 7
     * leetcode 0ms
     * @param nums
     * @return
     */
    public static int solveV2(int[] nums){
        int l = 0, r = nums.length-1, mid = 0;
        while(l<r&& nums[l]>=nums[r]){
            mid = (l+r)/2; // l <= mid < r
            if(nums[mid]>nums[r]) l = mid + 1;
            else r = mid;
        }
        return nums[l];
    }


    /**
     *
     * 遍历
     * 时间 - n
     * 1 2 4 5 6 7 0
     * 7 0 1 2 4 5 6
     * 0 1 2 4 5 6 7
     * leetcode 0ms
     * @param nums
     * @return
     */
    public static int solveV1(int[] nums){
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]) return nums[i+1];
        }
        return nums[0];
    }
}
