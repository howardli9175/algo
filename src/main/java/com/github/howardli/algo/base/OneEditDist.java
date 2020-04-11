package com.github.howardli.algo.base;

/**
 * Question:
 * Given two strings S and T, determine if they are both one edit distance apart.
 * Hint:
 * 1. If | n – m | is greater than 1, we know immediately both are not one-edit distance apart.
 * 2. It might help if you consider these cases separately, m == n and m ≠ n.
 * 3. Assume that m is always ≤ n, which greatly simplifies the conditional statements.
 * If m > n, we could just simply swap S and T.
 * 4. If m == n, it becomes finding if there is exactly one modified operation.
 * If m ≠ n, you do not have to consider the delete operation.
 * Just consider the insert operation in T.
 */
public class OneEditDist {
    public static void main(String[] args) {
        System.out.println(solveV1Simple("abc", "a"));//false
        System.out.println(solveV1Simple("abc", "acd"));//false
        System.out.println(solveV1Simple("abc", "ad"));//false
        System.out.println(solveV1Simple("a", ""));//true
        System.out.println(solveV1Simple("abc", "ab"));//true
        System.out.println(solveV1Simple("abc", "acc"));//true
    }

    public static boolean solveV1Simple(String s, String t) {
        int max = s.length(), min = t.length();
        if(min>max) return solveV1Simple(t,s);
        int shift = max-min;
        if(shift>1) return false;
        int i =0; // shift=1/0
        while(i<min&&s.charAt(i)==t.charAt(i))i++;
        // max-min==1, append
        if(i==min) return shift>0; // s==t return false;
        if(shift==0) i++; // max-min==0, modify
        while (i < min && s.charAt(i + shift) == t.charAt(i)) i++;
        return i==min;
    }


    /**
     *
     * 时间 - n
     * 空间 - 1
     * @param s
     * @param t
     * @return
     */
    public static boolean solveV1(String s, String t) {
        boolean res = false;
        if (Math.abs(s.length() - t.length()) == 0) {
            char[] scs = s.toCharArray();
            char[] tcs = t.toCharArray();
            int cntDiff = 0;
            for (int i = 0; i < scs.length; i++) {
                if (scs[i] != tcs[i]) {
                    cntDiff++;
                    if (cntDiff > 1) return false;
                }
            }
            res = cntDiff == 1;
        }
        if (Math.abs(s.length() - t.length()) == 1) {
            char[] csMin = s.toCharArray();
            char[] csMax = t.toCharArray();
            if (s.length() > t.length()) {
                char[] tmp = csMax;
                csMax = csMin;
                csMin = tmp;
            }
            int i = 0;
            while (i < csMin.length && csMin[i] == csMax[i]) i++;
            // i==csMin.length-1, max包含min
            // i<csMin.length-1,
            // csMin.lenght==0,
            while (i < csMin.length && csMin[i] == csMax[i + 1]) i++;
            res = i == csMin.length;
        }
        return res;
    }
}
