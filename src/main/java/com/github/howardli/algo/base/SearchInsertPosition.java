package com.github.howardli.algo.base;

public class SearchInsertPosition {
    public static void main(String[] args){
        int[] arr = new int[]{1,3,5,6};
        System.out.println(searchInsertV1(arr, 5));//2
        System.out.println(searchInsertV1(arr, 2));//1
        System.out.println(searchInsertV1(arr, 7));//4
        System.out.println(searchInsertV1(arr, 0));//0
    }

    /**
     *
     * @param arrSorted
     * @param target
     * @return
     */
    private static int searchInsertV1(int[] arrSorted, int target){
        int l = 0, r = arrSorted.length-1;
        while(l<r){
            System.out.println(String.format("left - %d, right - %d", l, r));
            int mid = (l+r)/2;
            if(arrSorted[mid]<target) l = mid+1;
            else r = mid;
        }
        if(arrSorted[l]>=target)return l;
        else return l+1;
    }
}
