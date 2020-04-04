package com.github.howardli.algo.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.function.ToIntFunction;

public class ArrayUtil {

    static Random rand = new Random(System.currentTimeMillis());

    public static void printArray(int[] arr){
        System.out.println(Arrays.toString(arr));
    }

    public static void swapArray(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    public static int[] sortedIntArray(int size, int bound, boolean reverse){
        Integer[] rawRes = randomIntegerArray(size,bound);
        if(reverse){
            Arrays.sort(rawRes, Collections.reverseOrder());

        }else{
            Arrays.sort(rawRes);
        }
        int[] res = Arrays.stream(rawRes).mapToInt(new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return value;
            }
        }).toArray();
        return res;

    }

    public static int[] randomIntArray(int size, int bound){
        return Arrays.stream(randomIntegerArray(size,bound)).mapToInt(new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return value;
            }
        }).toArray();
    }

    public static Integer[] randomIntegerArray(int size, int bound){
        Integer[] res = new Integer[size];
        for (int i = 0; i < size; i++) {
            res[i] = rand.nextInt(bound);
        }
        return res;

    }
}
