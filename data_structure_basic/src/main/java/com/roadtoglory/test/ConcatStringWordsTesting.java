/*
 * Copyright (c) 2026.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.test;
/*
*
*

This class ConcatStringWordsTesting is created and managed by subhe
Created on 26-01-2026 at 09:20 for the project Leetcode testing 

*
*
*/

import com.roadtoglory.practice.twopointers.ConcatStringWords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConcatStringWordsTesting {

    private final ConcatStringWords solution = new ConcatStringWords();

    @Test
    void testBasicExample() {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};

        List<Integer> result = solution.findSubstringChatGPT(s, words);

        assertEquals(List.of(9, 0), result);
    }

    @Test
    void testWordsWithDuplicates() {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good"};

        List<Integer> result = solution.findSubstringChatGPT(s, words);

        assertEquals(List.of(8), result);
    }

    @Test
    void testOverlappingMatches() {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar", "foo", "the"};

        List<Integer> result = solution.findSubstringChatGPT(s, words);

        assertEquals(List.of(6, 9, 12), result);
    }

    @Test
    void testNoMatch() {
        String s = "abcdefg";
        String[] words = {"hi", "jk"};

        List<Integer> result = solution.findSubstringChatGPT(s, words);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testSingleWord() {
        String s = "aaaaaa";
        String[] words = {"aa"};

        List<Integer> result = solution.findSubstringChatGPT(s, words);

        assertEquals(List.of(0, 1, 2, 3, 4), result);
    }

    @Test
    void testExactLengthMatch() {
        String s = "foobar";
        String[] words = {"foo", "bar"};

        List<Integer> result = solution.findSubstringChatGPT(s, words);

        assertEquals(List.of(0), result);
    }

    @Test
    void testStringShorterThanWindow() {
        String s = "foo";
        String[] words = {"foo", "bar"};

        List<Integer> result = solution.findSubstringChatGPT(s, words);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testEmptyWordsArray() {
        String s = "foobar";
        String[] words = {};

        List<Integer> result = solution.findSubstringChatGPT(s, words);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testRepeatedCharacters() {
        String s = "aaaaaaaaaaaaaa";
        String[] words = {"aaa", "aaa"};

        List<Integer> result = solution.findSubstringChatGPT(s, words);

        assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8), result);
    }
}
