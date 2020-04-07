package com.github.howardli.algo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
 * For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]
 * <p>
 * Example Questions Candidate Might Ask:
 * Q: What if the given array is empty?
 * A: Then you should return [“0->99”] as those ranges are missing.
 * Q: What if the given array contains all elements from the ranges?
 * A: Return an empty list, which means no range is missing.
 */
public class MissRange {
    public static void main(String[] args) {
        System.out.println(solveV1(new int[]{}, 0, 99));//["0->99"]
        System.out.println(solveV1(new int[]{50}, 0, 99));//["0->49","51->99"]
        System.out.println(solveV1(new int[]{1}, 0, 99));//["0","2->99"]
        System.out.println(solveV1(new int[]{98}, 0, 99));//["0->97","99"]
        System.out.println(solveV1(new int[]{0, 1, 3, 50, 75}, 0, 99));//[“2”, “4->49”, “51->74”, “76->99”]
        // []
        System.out.println(solveV2(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99}, 0, 99));//[“2”, “4->49”, “51->74”, “76->99”]
    }


    /**
     * 时间 - n
     * 写法更优雅，统一的处理了首尾的边界问题
     *
     * @param arr
     * @param min
     * @param max
     * @return
     */
    public static List<String> solveV2(int[] arr, int min, int max) {
        List<String> res = new ArrayList<>();
        int prev = min - 1;
        for (int i = 0; i <= arr.length; i++) {
            int cur = (i == arr.length) ? max + 1 : arr[i];
            if (cur - prev >= 2) {
                String range = cur - 1 == prev + 1 ?
                        String.valueOf(prev + 1)
                        : String.format("%d->%d", prev + 1, cur - 1);
                res.add(range);
            }
            prev = cur;
        }
        return res;
    }

    /**
     * 时间 - n
     *
     * @param arr
     * @return
     */
    public static List<String> solveV1(int[] arr, int min, int max) {
        int n = arr.length;
        List<String> res = new ArrayList<>();
        if (n == 0) res.add(String.format("%d->%d", min, max));
        else {
            // 处理首个元素
            if (arr[0] - min >1) res.add(String.format("%d->%d", min, arr[0] - 1));
            else if (arr[0] - min ==1) res.add(String.format("%d", min));
            // 从第1个元素开始，比较当前元素与前一个元素
            for (int i = 1; i < n; i++) {
                if (arr[i] - arr[i - 1] > 2)
                    res.add(String.format("%d->%d", arr[i - 1] + 1, arr[i] - 1)); // find range miss
                else if (arr[i] - arr[i - 1] == 2) res.add(String.format("%d", arr[i - 1] + 1)); // find single miss
            }
            // 处理最后一个元素
            if (max - arr[n - 1] >1 ) res.add(String.format("%d->%d", arr[n - 1] + 1, max));
            else if (max - arr[n - 1] ==1) res.add(String.format("%d", max));
        }
        return res;
    }
}
