package com.github.howardli.algo.base;

import com.github.howardli.algo.util.ArrayUtil;

public class TwoSumSorted {
    public static void main(String[] args){
        int[] arr = new int[]{2,7,11,15};
        ArrayUtil.printArray(solveV1(arr,9));
        ArrayUtil.printArray(solveV1(arr,13));
        ArrayUtil.printArray(solveV1(arr,17));
        ArrayUtil.printArray(solveV1(arr,18));
        ArrayUtil.printArray(solveV1(arr,22));
        ArrayUtil.printArray(solveV1(arr,26));
        ArrayUtil.printArray(solveV1(arr,262));
    }

    /**
     *
     * 两个指针，
     *
     * 时间 - n
     * 空间 - 1
     * @param arr
     * @param target
     * @return
     */
    public static int[] solveV1(int[] arr, int target){
        int l = 0, r = arr.length-1;
        while(l<r) {
            while (l < r && arr[l] + arr[r] < target) l++;
            if (l < r && arr[l] + arr[r] == target) return new int[]{l, r};
            while (l < r && arr[l] + arr[r] > target) r--;
            if (l < r && arr[l] + arr[r] == target) return new int[]{l, r};
        }
        return new int[]{-1,-1};
    }
}
