/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.leetcodes.twopointers;
/*
*
*

This class LongestString is created and managed by subhe
Created on 07-01-2026 at 08:01 for the project Leetcode Problem

*
*
*/

public class LongestString {

    public static void main(String[] args) {

        String inp = "au";
        System.out.println("The longest Substring is " + new LongestString().lengthOfLongestSubstring(inp));
    }

    public int lengthOfLongestSubstring(String s) {

        StringBuilder output = new StringBuilder();
        if (s.length() <= 1) {
            return s.length();
        }
        int longestSubstringLength = 0;
        int i = 0;
        while (i < s.length()) {
            if (!output.isEmpty() && output.toString().contains(String.valueOf(s.charAt(i)))) {
                if (output.length() > longestSubstringLength) {
                    longestSubstringLength = output.length();
                }
                int dupInd = output.indexOf(String.valueOf(s.charAt(i)));
                output = new StringBuilder(output.substring(dupInd + 1));

            }
            output.append(s.charAt(i));
            i++;

        }
        if (longestSubstringLength == 0 && output.length() > 0) {
            return output.length();
        }
        return longestSubstringLength;
    }
}
