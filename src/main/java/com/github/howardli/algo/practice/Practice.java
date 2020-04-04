package com.github.howardli.algo.practice;

import com.github.howardli.algo.util.ArrayUtil;

import java.util.Random;

public class Practice {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.sortedIntArray(10, 15, false);
        ArrayUtil.printArray(arr);
        int target = new Random(System.currentTimeMillis()).nextInt(10);
        System.out.println(target);
        System.out.println(findNoRecursion(arr, target));
    }

    public static int findNoRecursion(int[] arr, int target){
        int index = -1;
        int left = 0, right = arr.length;
        while(left<=right){
            int mid = (left+right)/2; // left < mid < right
            if(arr[mid]==target){
                return mid;
            }else if(target<arr[mid]){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return index;
    }

    public static int findRecursion(int[] arr, int target){
        return find1(arr, target, 0, arr.length-1);
    }

    public static int find1(int[] arr, int target, int left, int right){
        if(left<right){
            int mid = (left+right)/2;
            if(target==arr[mid]){
                return mid;
            }else{
                return target<arr[mid]?find1(arr,target, left,mid-1):find1(arr,target,mid+1, right);
            }
        }else{
            return arr[left]==target?left:-1;
        }
    }


}
