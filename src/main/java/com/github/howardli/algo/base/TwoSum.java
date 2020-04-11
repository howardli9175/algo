package com.github.howardli.algo.base;

import com.github.howardli.algo.util.ArrayUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 示例:

 给定 nums = [2, 7, 11, 15], target = 9

 因为 nums[0] + nums[1] = 2 + 7 = 9
 所以返回 [0, 1]

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/two-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    public static void main(String[] args){
        int[] arr = new int[]{2,7,11,15};
        ArrayUtil.printArray(solveV3(arr,9));
        ArrayUtil.printArray(solveV3(arr,13));
        ArrayUtil.printArray(solveV3(arr,17));
        ArrayUtil.printArray(solveV3(arr,18));
        ArrayUtil.printArray(solveV3(arr,22));
        ArrayUtil.printArray(solveV3(arr,26));
        ArrayUtil.printArray(solveV3(arr,262));
    }


    /**
     *
     * 用一个map来存已经见过的数
     * 一次循环：
     * 1、把数和位置放进map，如果
     * 时间 - n
     * 空间 - n
     * @param arr
     * @param target
     * @return
     */
    public static int[] solveV3(int[] arr, int target){
        Map<Integer, Integer> val2Idx = new HashMap<Integer,Integer>();
        for(int i=0;i<arr.length;i++){
            if(val2Idx.containsKey(target-arr[i])){
                return new int[]{val2Idx.get(target-arr[i]), i};
            }
            val2Idx.put(arr[i],i);
        }
        return new int[]{-1,-1};
    }


    /**
     *
     * 暴力
     * 两层循环
     *
     * 时间 - n2
     * 空间 - 1
     * @param arr
     * @param target
     * @return
     */
    public static int[] solveV2(int[] arr, int target){
        for(int i=0;i<arr.length-1;i++){
            for(int j = i+1;j<arr.length;j++){
                if(arr[i]+arr[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 时间 - n2
     * 空间 - 1
     * @param arr
     * @param target
     * @return
     */
    public static int[] solveV1(int[] arr, int target){
        return solveV1Sub(arr, target, arr.length-1);
    }

    private static int[] solveV1Sub(int[] arr, int target, int right) {
        if(right<1){
            return new int[]{-1,-1};
        }
        for(int i=0;i<right;i++){
            if(arr[i]+arr[right]==target){
                return new int[]{i,right};
            }
        }
        return solveV1Sub(arr, target, right-1);
    }
}
