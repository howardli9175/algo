package com.github.howardli.algo.base;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * <p>
 * Example Questions Candidate Might Ask:
 * Q: What about an empty string? Is it a valid palindrome?
 * A: For the purpose of this problem, we define empty string as valid palindrome.
 */
public class ValidPalindrome {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(isValidPalindromeV1("aba"));//true
        System.out.println(isValidPalindromeV1("abab"));//false
        System.out.println(isValidPalindromeV1("A man, a plan, a canal: Panama"));//true
        System.out.println(isValidPalindromeV1("race a car"));//false
        System.out.println(isValidPalindromeV1(",,,,a,"));//true
        while(true){
            Thread.sleep(10000);
        }
    }

    private static boolean isValidPalindromeV1(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            } else if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            } else if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

}
