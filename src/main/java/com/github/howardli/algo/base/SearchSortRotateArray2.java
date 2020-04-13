package com.github.howardli.algo.base;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 *
 * You are given a target value to search. If found in the array return true, otherwise return false.
 *
 * Example 1:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * Follow up:
 *
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 *
 */
public class SearchSortRotateArray2 {
    public static void main(String[] args){
//        System.out.println(solveV1(new int[]{2,5,6,0,0,1,2},0));// true
//        System.out.println(solveV1(new int[]{2,5,6,0,0,1,2},3));// false
//        System.out.println(solveV1(new int[]{3,1},3));// true
        System.out.println(solveV3(new int[]{1,3,5},3));// true
    }

    /**
     * 2,5,6,0,0,1,2 - Al>Ar
     * 2,5,6,0,0,1,2 - Al>Ar
     * 0,0,1,2,2,5,6 - Al<Ar
     * 总有一半是非降序的，依然。但是怎么判断哪一半？
     * 3,2,3,3,3
     *
     * @param nums
     * @param target
     * @return
     */
    public static boolean solveV1(int[] nums, int target){
        int l = 0, r = nums.length-1, mid = -1;
        while(l<r){
            mid = (l+r)/2; // l<=mid<r
            if(nums[mid]<nums[r]){ // 右侧有序
                // 并且target在右侧有序区间内
                if(target<=nums[r]&&target>nums[mid]) l = mid+1;
                else r = mid;
            }else if (nums[l]< nums[mid]){ // 左侧有序
                // 并且target在左侧有序区间内
                if(target<=nums[mid]&&target>=nums[l]) r = mid;
                else l = mid+1;
            }else{ //  nums[l]>=nums[mid]>=nums[r]  因为必有一半是非降序，所以必有一个等号成立
                // 9  9  3 - 左侧都是9
                // 9  3  3 - 右侧都是3
                // 3 1 3 3 3 3 3 - 必有一侧都是3
                // 能通过常数次比较确定是哪一侧吗？不能
                if(nums[l]==nums[mid]&&target!=nums[l]) l++;
                else r--;
            }
        }
        return nums.length>0&&nums[l]==target; // nums为空
    }


    public static boolean solveV2(int[] nums, int target){
        int l = 0, r = nums.length-1, mid = -1;
        while(l<r){
            mid = (l+r)/2; // l<=mid<r
            if(nums[mid]<nums[r] || nums[l] > nums[mid]){ // 右侧有序,或者左侧肯定不有序
                // 并且target在右侧有序区间内
                if(target<=nums[r]&&target>nums[mid]) l = mid+1;
                else r = mid;
            }else if (nums[l]< nums[mid]||nums[mid]>nums[r]){ // 左侧有序,或者右侧肯定不有序
                // 并且target在左侧有序区间内
                if(target<=nums[mid]&&target>=nums[l]) r = mid;
                else l = mid+1;
            }else{ //  nums[l]>=nums[mid]>=nums[r]  因为必有一半是非降序，所以必有一个等号成立
                // !( nums[mid]<nums[r] || nums[l] > nums[mid])
                // nums[mid]>=nums[r] and nums[l] <= nums[mid]
                // !( nums[l]< nums[mid]||nums[mid]>nums[r] )
                // nums[mid]<= nums[r] and nums[l]>= nums[mid]
                // nums[mid]=nums[r]=nums[l]
                // 能通过常数次比较确定是哪一侧吗？不能
                r--;
            }
        }
        return nums.length>0&&nums[l]==target; // nums为空
    }


    public static boolean solveV3(int[] nums, int target){
        int start = 0, end = nums.length - 1, mid = -1;
        while(start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //If we know for sure right side is sorted or left side is unsorted
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
                //If we know for sure left side is sorted or right side is unsorted
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
                //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
                //any of the two sides won't change the result but can help remove duplicate from
                //consideration, here we just use end-- but left++ works too
            } else {
                end--;
            }
        }

        return false;
    }

}
