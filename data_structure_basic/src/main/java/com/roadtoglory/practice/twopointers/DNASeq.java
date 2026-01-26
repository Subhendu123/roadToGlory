/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.practice.twopointers;
/*
*
*

This class DNASeq is created and managed by subhe
Created on 15-01-2026 at 09:57 for the project Leetcode Problem

*
*
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DNASeq {

    public static void main(String[] args) {
        List<String> result = new DNASeq().findRepeatedDnaSequences("AAAAAAAAAAAA");
        if (result != null && !result.isEmpty()) {
            result.stream().forEach(System.out::println);
        }

    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() <= 10) {
            return res;
        }
        if (s.length() == 10) {
            res.add(s);
            return res;
        }
        Map<String, Integer> resultMap = new HashMap<>();
        String sub = s.substring(0, 10);
        resultMap.put(sub, 1);
        int l = 1;


//        for (int i = 0; (i + 10) <= s.length(); i++) {
//            String sub = s.substring(l, i + 10);
//            if (resultMap.containsKey(sub) && !res.contains(sub)) {
//                res.add(sub);
//            }
//            else {
//                resultMap.put(sub, 1);
//            }
//            l++;
//        }

        // Sliding window, above non sliding window
        for (int i = 10; i < s.length(); i++) {
            sub = s.substring(l, i + 1);
            if (resultMap.containsKey(sub) && !res.contains(sub)) {
                res.add(sub);
            }
            else {
                resultMap.put(sub, 1);
            }
            l++;
        }
        return res;


    }


}
