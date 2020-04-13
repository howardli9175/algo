package com.github.howardli.algo.base;

/**
 * Code it now: https://oj.leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * Difficulty: Medium, Frequency: High
 * Question:
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 */
public class MinSortRotateArray {
    public static void main(String[] args) {
        System.out.println(solveV2(new int[]{4, 5, 6, 7, 0, 1, 2}));//0
        System.out.println(solveV2(new int[]{3, 4, 5, 1, 2}));//1
        System.out.println(solveV2(new int[]{1, 2, 3, 4, 5}));//1
        batchTest();

    }


    /**
     * todo
     * v1 use 852 ms
     * v2 use 18 ms
     */
    public static void batchTest() {
        long v1Time = 0, v2Time = 0, begin = 0, end = 0;
        int batch = 65536;
        for (int i = 0; i < batch; i++) {
            int[] a = testArrays(batch, i);
            begin = System.currentTimeMillis();
            solveV1(a);
            end = System.currentTimeMillis();
            v1Time += (end - begin);
            begin = System.currentTimeMillis();
            solveV2(a);
            end = System.currentTimeMillis();
            v2Time += (end - begin);
        }
        System.out.println(String.format("v1 use %d ms", v1Time));
        System.out.println(String.format("v2 use %d ms", v2Time));
    }

    public static int[] testArrays(int size, int batch) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[(batch + i) % size] = i;
        }
        return res;
    }

    /**
     * 二分查找
     * 时间 - logn
     * 4 5 6 7 0 1 2
     * 5 6 7 0 1 2 4
     * 1 2 4 5 6 7 0
     * 7 0 1 2 4 5 6
     * 0 1 2 4 5 6 7
     * leetcode 0ms
     *
     * @param nums
     * @return
     */
    public static int solveV2(int[] nums) {
        int l = 0, r = nums.length - 1, mid = 0;
        while (l < r && nums[l] >= nums[r]) {
            mid = (l + r) / 2; // l <= mid < r
            if (nums[mid] > nums[r]) l = mid + 1; // 一定不是mid，一定在mid右侧
            else r = mid; // nums[mid]<nums[r]<nums[l]
        }
        return nums[l];
    }


    /**
     * 遍历
     * 时间 - n
     * 1 2 4 5 6 7 0
     * 7 0 1 2 4 5 6
     * 0 1 2 4 5 6 7
     * leetcode 0ms
     *
     * @param nums
     * @return
     */
    public static int solveV1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return nums[i + 1];
        }
        return nums[0];
    }
}
