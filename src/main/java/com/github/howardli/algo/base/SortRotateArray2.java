package com.github.howardli.algo.base;

/**
 *
 * Code it now: https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * Difficulty: Hard, Frequency: Medium
 *
 * Question:
 * If the rotated sorted array could contain duplicates?
 * Is your algorithm still O(log n) in runtime complexity?
 *
 */
public class SortRotateArray2 {
    public static void main(String[] args){
        System.out.println(solveV2(new int[]{1,3,5}));//1
        System.out.println(solveV2(new int[]{2,2,2,0,0,1}));//0
        System.out.println(solveV2(new int[]{3,3,1,3}));//1
    }

    /**
     * 二分查找
     * Al=Ar todo
     * @param nums
     * @return
     */
    public static int solveV2(int[] nums){
        int l = 0, r = nums.length-1, mid = 0;
        while(l<r&&nums[l]>=nums[r]){
            mid = (l+r)/2; // l <= mid < r
            if(nums[mid]>nums[r]) l = mid + 1;
            else if(nums[mid]<nums[r]) r = mid;
            else l++;
        }
        return nums[l];
    }


    /**
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
