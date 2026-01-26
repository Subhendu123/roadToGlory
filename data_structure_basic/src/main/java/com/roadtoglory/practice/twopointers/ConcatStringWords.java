/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.practice.twopointers;
/*
*
*

This class ConcatStringWords is created and managed by subhe
Created on 19-01-2026 at 22:37 for the project Leetcode

*
*
*/

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConcatStringWords {

    public static void main(String[] args) {


        String[] words4 = {"ab", "ba", "ba"};
        List<Integer> result4 = new ConcatStringWords().findSubstring3(
                "ababaab", words4);
        System.out.println("Result for 1. : " + result4);

        String[] words3 = {"aa", "aa"};
        List<Integer> result3 = new ConcatStringWords().findSubstring3(
                "aaaaaaaaaaaaaa", words3);
        System.out.println("Result for 1. : " + result3);


        String[] words = {"fooo", "barr", "wing", "ding", "wing"};
        List<Integer> result = new ConcatStringWords().findSubstring3(
                "lingmindraboofooowingdingbarrwingmonkeypoundcake", words);
        System.out.println("Result for 1. : " + result);


        String[] words2 = {"bar", "foo", "the"};
        List<Integer> result2 = new ConcatStringWords().findSubstring3(
                "barfoofoobarthefoobarman", words2);
        System.out.println("Result for 2. : " + result2);
    }


    public List<Integer> findSubstring3(String s, String[] words) {

        List<Integer> result = new ArrayList<>();
        Map<String, Integer> wordMap = new HashMap<>();
        Map<String, Integer> wordMapOrg = new HashMap<>();
        int lowestSearchIndex = -1;
//        StringBuilder sameWordBuilder = new StringBuilder();

        if (words.length == 0) {
            return result;
        }
        for (int i = 0; i < words.length; i++) {

            wordMap.put(words[i], wordMap.getOrDefault(words[i], 0) + 1);
            wordMapOrg.put(words[i], wordMapOrg.getOrDefault(words[i], 0) + 1);
//            sameWordBuilder.append(words[i]);

            // TODO Perform an additional check to determine if all the word exists or not.
            int indexWord = s.indexOf(words[i]);
            if (indexWord > -1) {
                if (lowestSearchIndex == -1) {
                    lowestSearchIndex = indexWord;
                }
                lowestSearchIndex = Math.min(lowestSearchIndex, indexWord);
            }
            else {
                // TODO Must return empty list if any of them does not exist
                result = null;
                break;
            }


        }

        if (result == null) {
            return new ArrayList<>();
        }


        int l = lowestSearchIndex;
        int eachWordLen = words[0].length();
        int r = l + eachWordLen;
        boolean endTouched = false;


        /*if (wordMap.size() == 1 && wordMap.get(words[0]) > 1) {

            // aaa case
            eachWordLen = sameWordBuilder.length();
            r = l + eachWordLen;

            while (r <= s.length()) {
                String subStr = s.substring(r - eachWordLen, r);
                endTouched = r == s.length();

                if (sameWordBuilder.toString().equalsIgnoreCase(subStr)) {
                    result.add(l);
                    l = l + 1;
                    r = r + 1;
                }
            }
            return result;

        }*/

        while (r <= s.length()) {

            String subStr = s.substring(r - eachWordLen, r);
            endTouched = r == s.length();
            if (wordMap.containsKey(subStr)) {
                // existing word in the list of words
                int count = wordMap.get(subStr);
                if (count > 1) {
                    wordMap.put(subStr, count - 1);
                }
                else {
                    // only one word. no repeating
                    wordMap.remove(subStr);
                }
                r = r + eachWordLen;
            }
            else {
                // this subseq is not matching
                l = l + eachWordLen;
                r = l + eachWordLen;
//                    l = r - eachWordLen;
                wordMap = new HashMap<>(wordMapOrg);

            }

            if (wordMap.isEmpty()) {
                result.add(l);
                wordMap = new HashMap<>(wordMapOrg);
                l = l + eachWordLen;
                r = l + eachWordLen;
            }

            if (endTouched) {
                break;
            }


        }
        return result;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int perWordLen = words[0].length();
        int totalLen = perWordLen * words.length;
        int index = 0;
        while (index + totalLen <= s.length()) {

            String subString = s.substring(index, index + totalLen);
            int count = 0;
            for (int i = 0; i < words.length; i++) {
                if (subString.contains(words[i])) {
                    subString = subString.replaceFirst(words[i], "");
                    count++;
                }
            }
            if (count == words.length) {
                result.add(index);
            }
            index = index + perWordLen;

        }

        return result;

    }

    public List<Integer> findSubstring(String s, String[] words) {
        HashSet<String> combinationsList = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        for (String word : words) {
            StringBuilder wordToAdd = new StringBuilder(word);
            boolean notRequired = false;

            if (!s.contains(word)) {
                notRequired = true;
                break;
            }

            for (String cword : words) {
                if (!word.equalsIgnoreCase(cword)) {
                    if (!s.contains(cword)) {
                        notRequired = true;
                        break;
                    }
                    wordToAdd.append(cword);
                }

            }
            if (notRequired) {
                break;
            }

            System.out.println(wordToAdd);

            if (!combinationsList.contains(wordToAdd.toString())) {
                combinationsList.add(wordToAdd.toString());
                // check if this combi is present in string s and how many times
                if (s.contains(wordToAdd)) {
                    Pattern pattern = Pattern.compile("(?i)" + Pattern.quote(wordToAdd.toString()));
                    Matcher matcher = pattern.matcher(s);

                    int count = 0;

                    while (matcher.find()) {
                        count++;
                        result.add(matcher.start());
                    }
                }
            }
        }

        return result;

    }
}
