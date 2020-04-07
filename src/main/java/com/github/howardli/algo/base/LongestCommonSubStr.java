package com.github.howardli.algo.base;

/**
 *
 * https://www.lintcode.com/problem/longest-common-substring/description
 * str1 str2
 * <p>
 * if one is empty, return empty
 *
 * Given two strings, find the longest common substring.
 *
 * Return the length of it.
 *
 * The characters in substring should occur continuously in original string. This is different with subsequence.
 */
public class LongestCommonSubStr {

    public static void main(String[] args) {
//        System.out.println(solveV1Brief("", ""));//""
//        System.out.println(solveV1Brief("abc", ""));//""
//        System.out.println(solveV1Brief("abc", "de"));//""
//        System.out.println(solveV1Brief("abc", "b"));//"b"
//        System.out.println(solveV1Brief("abc", "bcd"));//"bc"
//        System.out.println(solveV1Brief("abacdfgdcaba", "abacdgfdcaba"));//"abacd"
//        System.out.println(solveV1Brief("abacdgfdcaba", "abacdfgdcaba"));//"abacd"
//        System.out.println(solveV1Brief("YXYXXYYYYXXYYYYXYYXXYYXXYXYYYYYYXYXYYXYXYYYXXXXXX", "YXXXXXY"));//"abacd"
//        System.out.println(solveV2("banana", "cianaic"));//"ana"
        System.out.println(solveV2("abc", "a"));//"ana"
    }


    public static String solveV2(String A, String B) {
        int[][] f = new int[A.length() + 1][B.length() + 1];
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) f[i][j] = f[i - 1][j - 1] + 1;
                else f[i][j] = 0;
            }
        }
        int max = 0, bi = -1;
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (f[i][j] > max) {
                    max = f[i][j];
                    bi = j;
                }
            }
        }
        return B.substring(bi - max, bi);
    }


    /**
     * 时间 - mn
     * 空间 - mn
     *
     * @param A
     * @param B
     * @return
     */
    public static int solveV2RetLength(String A, String B) {
        int[][] f = new int[A.length() + 1][B.length() + 1];
        int max = 0;
        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    if (f[i][j] > max) max = f[i][j];
                } else f[i][j] = 0;
            }
        }
        return max;
    }

    /**
     * 时间 - (m+n)*n
     * 空间 - 1
     * <p>
     * 有必要区分长和短吗
     *
     * @param min
     * @param max
     * @return
     */
    public static String solveV1(String min, String max) {
        int minLen = min.length(), maxLen = max.length();
        if (min.length() > max.length()) {
            minLen = max.length();
            maxLen = min.length();
            String tmp = max;
            max = min;
            min = tmp;
        }
        int maxCom = 0, stRes = -1, enRes = minLen;
        for (int i = 1 - minLen; i <= maxLen - 1; i++) {
            int st = -1, en = -1;
            for (int k = 0; k < minLen; k++) {
                int tmp = k + i;
                if (tmp >= 0 && tmp < maxLen) {
                    char maxChar = max.charAt(tmp);
                    char minChar = min.charAt(k);
                    if (maxChar == minChar) {
                        if (st == -1) st = k;
                        else en = k + 1;
                    } else break;
                }
            }
            if (st >= 0 && en - st > maxCom) {
                maxCom = en - st;
                stRes = st;
                enRes = en;
            }
        }
        return maxCom > 0 ? min.substring(stRes, enRes) : "";
    }


    /**
     * 时间 - (m+n)*n
     * 空间 - 1
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String solveV1Brief(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length(); // 去掉减一点内存
        int maxCom = 0, stRes = 0, enRes = 0;
        for (int i = 1 - n1; i <= n2 - 1; i++) {
            int st = n1;
            int k = i < 0 ? -i : 0; //k + i >= 0
            int r = i < n2 - n1 ? n1 : n2 - i; // k + i < n2, n1< n2-i?n1:n2-i
            if (r - k <= maxCom) continue; // r-k是本次检查的上限，
            // 必须遍历完不能break，因为子串可以在最后。同时可能会有多个公共子串
            for (; k < r; k++) { // 两个指针都合法
                char maxChar = s2.charAt(k + i); // 去掉减一点内存
                char minChar = s1.charAt(k); // 去掉减一点内存
                if (maxChar == minChar) {
                    if (st == n1) st = k; // 记录头指针, 全不相等时，st=n1, k-st<0
                } else {
                    if (st != n1) { // find common substr
                        if (k - st > maxCom) {
                            maxCom = k - st;
                            stRes = st;
                            enRes = k;
                        }
                        st = n1; // 头指针重新初始化
                    }
                }
            }
            // k = r， 最后一个未处理的子串
            if (k - st > maxCom) {
                maxCom = k - st;
                stRes = st;
                enRes = k;
            }
        }
        return maxCom > 0 ? s1.substring(stRes, enRes) : "";
    }


    /**
     * 时间 - (m+n)*n
     * 空间 - 1
     *
     * @param A
     * @param B
     * @return
     */
    public static int solveV1BriefRetLength(String A, String B) {
        int maxCom = 0, st = 0, k = 0, r = 0, i = 1 - A.length(); // 可以在lintcode上缩短时间 i = 1 - A.length()
        for (; i <= B.length() - 1; i++) {
            st = A.length();
            k = i < 0 ? -i : 0; //k + i >= 0
            r = i < B.length() - A.length() ? A.length() : B.length() - i; // k + i < n2, n1< n2-i?n1:n2-i
            if (r - k <= maxCom) continue; // r-k是本次检查的上限，
            int tmp = 0;
            for (; k < r; k++) { // 两个指针都合法
                if (B.charAt(k + i) == A.charAt(k)) {
                    if (st == A.length()) st = k; // 记录头指针, 全不相等时，st=n1, k-st<0
                } else {
                    if (st != A.length()) { // find common substr
                        if (k - st > maxCom) {
                            maxCom = k - st;
                        }
                        st = A.length(); // 头指针重新初始化
                    }
                }
            }
            // 末指针为k, 全都相等也ok
            if (k - st > maxCom) {
                maxCom = k - st;
            }
        }
        return maxCom;
    }

    /**
     * 时间 - (m+n)*n
     * 空间 - 1
     *
     * @param A
     * @param B
     * @return
     */
    public static int solveV1BriefRetLengthV2(String A, String B) {
        int maxCom = 0, st = 0, k = 0, r = 0, i = 1 - A.length(); // 可以在lintcode上缩短时间 i = 1 - A.length()
        for (; i <= B.length() - 1; i++) {
            // 两个指针都合法
            k = i < 0 ? -i : 0; //k + i >= 0
            r = i < B.length() - A.length() ? A.length() : B.length() - i; // k + i < n2, n1< n2-i?n1:n2-i
            if (r - k <= maxCom) continue; // r-k是本次检查的上限，
            st = 0;
            for (; k < r; k++) {
                if (B.charAt(k + i) == A.charAt(k)) {
                    st++; // 长度++
                } else {
                    if (st > maxCom) maxCom = st;// 更新最大值
                    st = 0; // 长度归0
                }
            }
            // 末尾相同
            if (st > maxCom) maxCom = st;
        }
        return maxCom;
    }
}
