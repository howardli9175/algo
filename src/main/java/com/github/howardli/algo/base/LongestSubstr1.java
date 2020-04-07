package com.github.howardli.algo.base;

import com.github.howardli.algo.util.StringUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 巧妙
 * https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for “abcabcbb” is “abc”,
 * which the length is 3.
 * For “bbbbb” the longest substring is “b”, with the length of 1.
 *
 */
public class LongestSubstr1 {

    public static void main(String[] args){
//        System.out.println(solveV1RetLength("bbbbb"));//1
//        System.out.println(solveV1RetLength("abcdefg"));//7
//        System.out.println(solveV1RetLength("abcabcbb"));//3
//        System.out.println(solveV1RetLength(""));//0
//        compareTestBatch(10);
//        System.out.println(String.format("loop count in solve %d", 34));
        compareTestSingle("udowjufnfeegzwepjxmgoblrbqxunjegyasqfrwxzsmpiodgpidvbmabrzvrxgvrildzbgslsefcburbvvdaqnnpnsicawcftpcjpkezdoropbmqelmtlxymelzgufxxdabnikaddzbzubjavyqfoietsvhnadcxhckvzzwqbzmpijvytgkairmpeyhiyexktpwpoyjceuajbpxgfzuimjvyykthszovkrljfnajfefdljnrcowyoxjbbvbidwlucznqylfqffjhcxpesxtwnqepisjzbszmgtynsutgtidqkhdxnxowgijsurpobwdgptmibcyskvykiirzmcxbzidjhpquptpeipvfodjazoczjkthdimtvjypdxyidxrtwlyebthpsvlrtkclggvmuyxafwqenpscifdbprwcsmfhrvaskunzqiptqrswqirxrqmmcbnrjwldepgjdhjoxivhgwwqwlltcyrkctklxcwnazbswxdnxoaohoneylctsfdspfrxzimuiwlgighjllarexdzznmtezzuxcjylhscudtigewudofdyjjplswmlixyimdmdmgdeduxsczqleroacujlcrjqtegamejmfmwypeasqgyjozdyhtxeutjgeqphfgmzhjryknntzlgscpjnezjelitlpnrlybbpdhaikqwowqaitqmdgfghrlbseoseciuldbkqgggomkwxgmjxcjojdvrczprsbmfohybgfjaulewlyybpfxbepzycnxuwuennfhjwzvjqqalyghpwqznkdvpwgnzkrwzifzzinhbgxsgbwdqnhdgfabdnuuszoaqjsbnqxylbkpnujfohjjlkpevlrpednbqtigfseamyriqcqpbiuvxsihjvrrfnzamujdqvuqeugipwqlaguesffwvqvaldsnbqpdehlbmtoseeiisaecwgkvcbpidowsbkpurfqhxybrqrtdilannzigcdpbxbvyz");
    }

    /**
     * rxfjxrgzdfzwrdmgtivlhfajaykaphathbjkhenicfgsklfbzkglevdutpekogaxyofdhyhhpkyzyizwwylgftwncfwpvpjwwtcu
     * lzhnclztpaypjwxglvcbtlgokiyiizuutakdnreqdudcszaktuncwdgcnhpedtldfxbzbrfebqmknqioupsncleozhtmdcgwlzsu
     * @param caseStr
     */
    private static void compareTestSingle(String caseStr){
        long begin = 0, end = 0, goodTime = 0, badTime = 0;
        int goodRes = 0, badRes = 0;
        begin = System.currentTimeMillis();
        goodRes = solveV2(caseStr);
        end = System.currentTimeMillis();
        goodTime = end-begin;
        begin = System.currentTimeMillis();
        badRes = solveV1(caseStr);
        end = System.currentTimeMillis();
        badTime = end-begin;
        System.out.println(String.format("result %d vs %d, time %d vs %d, case %s", goodRes, badRes, goodTime, badTime, caseStr));
    }

    private static void compareTestBatch(int testCount){
        long begin = 0, end = 0, goodTime = 0, badTime = 0;
        int goodRes = 0, badRes = 0;
        for(int i=0;i<testCount;i++){
            String caseStr = StringUtil.getRandomLowerLetterString(1000);
            begin = System.currentTimeMillis();
            goodRes = solveV2(caseStr);
            end = System.currentTimeMillis();
            goodTime = end-begin;
            begin = System.currentTimeMillis();
            badRes = solveV1(caseStr);
            end = System.currentTimeMillis();
            badTime = end-begin;
            System.out.println(String.format("result %d vs %d, time %d vs %d, case %s", goodRes, badRes, goodTime, badTime, caseStr));
        }
    }

    /**
     * 一层循环
     * 1、不重复则记录位置，重复则直接跳到相应位置。
     *
     * @param s
     * @return
     */
    private static int solveV3(String s){
        int[] charMap = new int[256];
        Arrays.fill(charMap, -1);
        int i = 0, maxLen = 0;
        for (int j = 0; j < s.length(); j++) {
            if (charMap[s.charAt(j)] >= i) {
                i = charMap[s.charAt(j)] + 1;
            }
            charMap[s.charAt(j)] = j;
            maxLen = Math.max(j - i + 1, maxLen);
        }
        return maxLen;
    }

    /**
     * 一层循环：
     * 1、不重复则记录存在，重复则循环移动指针
     *
     * @param s
     * @return
     */
    private static int solveV2(String s) {
        boolean[] exist = new boolean[256];
        int i = 0, maxLen = 0;
        int loopCnt = 0;
        for (int j = 0; j < s.length(); j++) {
            while (exist[s.charAt(j)]) {
                loopCnt++;
                exist[s.charAt(i)] = false;
                i++;
            }
            exist[s.charAt(j)] = true;
            maxLen = Math.max(j - i + 1, maxLen);
        }
        System.out.println(String.format("loop count in solveV2 : %d", loopCnt));
        return maxLen;
    }


    /**
     * 两层循环：
     * 1、首字符位置
     * 2、找最长串
     *
     * 重复的地方在哪里呢？acbc????????, a开始的时候acbc停止，c开始的时候cbc停止
     * 如何优化呢？acbc停止的时候，直接从b开始。
     *
     * 时间 - n2 ???
     * 空间 - n
     * @param s
     * @return
     */
    private static int solveV1(String s) {
        int max = 0, n = s.length();
        Set<Character> tmp = new HashSet<>(n);
        int loopCnt = 0;
        for(int i=0;i<n;i++){
            int tmpMax = 0;
            for(int j = i;j<n;j++){
                loopCnt++;
                Character ch = s.charAt(j);
                if(tmp.contains(ch)){
                    break;
                }else{
                    tmp.add(ch);
                    tmpMax++;
                }
            }
            tmp.clear();
            if(tmpMax>max) {
                max = tmpMax;
                if(n-i-1<=max)break;// n=6时，max=3，则i>=2=n-max-1, max>=n-i-1即可以退出
            }
        }
        System.out.println(String.format("loop count in solveV1RetLength : %d", loopCnt));
        return max;
    }

}
