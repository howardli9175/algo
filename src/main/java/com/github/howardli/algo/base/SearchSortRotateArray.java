package com.github.howardli.algo.base;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 */
public class SearchSortRotateArray {
    public static void main(String[] args){
        System.out.println(solveV1(new int[]{4,5,6,7,0,1,2},0));//4
        System.out.println(solveV1(new int[]{4,5,6,7,0,1,2},3));//-1
    }

    /**
     * 4,5,6,7,0,1,2 - Al>Ar
     * 6,7,0,1,2,4,5 - Al>Ar
     * 0,1,2,4,5,6,7 - Al<Ar
     * 总有一半是有序的
     *
     * @param nums
     * @param target
     * @return
     */
    public static int solveV1(int[] nums, int target){
        int l = 0, r = nums.length-1, mid = -1;
        while(l<r){
            mid = (l+r)/2; // l<=mid<r
            if(nums[mid]<nums[r]){ // 右侧有序
                // 并且target在右侧有序区间内
                if(target<=nums[r]&&target>nums[mid]) l = mid+1;
                else r = mid;
            }else{ // 左侧有序
                // 并且target在左侧有序区间内
                if(target<=nums[mid]&&target>=nums[l]) r = mid;
                else l = mid+1;
            }
        }
        return nums.length>0&&nums[l]==target?l:-1; // nums为空
    }


}
