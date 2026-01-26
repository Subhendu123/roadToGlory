/*
 * Copyright (c) 2025.
 * This is created and managed by $(git config user.name)
 */

package com.roadtoglory.ds.trees;
/*
*
*

This class ExtendedLCBinarySearchTree is created and managed by subhe
Created on 26-06-2025 at 19:39 for the project LeetCode 

*
*
*/

import java.util.*;

public class ExtendedLCBinarySearchTree extends BinarySearchTreeImpl {

    public int[] findMode(Node root) {

        if (root == null) {
            return new int[1];
        }
        if (root.left == null && root.right == null) {
            return new int[]{root.data};
        }

        Stack<Node> nodeStack = new Stack<>();
        nodeStack.add(root);

        HashSet<Integer> allKeysList = new HashSet<>();
        List<Integer> finalOp = new ArrayList<>();
        List<Integer> finalOp2 = new ArrayList<>();


        Map<Integer, Integer> outputMap = new HashMap<>();
        while (!nodeStack.isEmpty()) {

            Node tr = nodeStack.pop();

            allKeysList.add(tr.data);
            if (outputMap.containsKey(tr.data)) {
                Integer cnt = outputMap.get(tr.data);
                outputMap.put(tr.data, ++cnt);
            }
            else {
                // first entry
                outputMap.put(tr.data, 1);
            }
            if (tr.left != null) {
                nodeStack.add(tr.left);
            }
            if (tr.right != null) {
                nodeStack.add(tr.right);
            }

        }
        long total = outputMap.values().stream().count();
        int elementCnt = allKeysList.size();
        int avgCountPerKey = Math.toIntExact(total / elementCnt);

        outputMap.forEach((k, v) -> {
            long indCount = allKeysList.stream().filter(element -> k != null && element.equals(k)).count();
            if (indCount > avgCountPerKey) {
                finalOp.add(k);
            }
            else {
                if (finalOp.isEmpty()) {
                    finalOp2.add(k);
                }
            }
        });
        List<Integer> resultList = finalOp.isEmpty() ? finalOp2 : finalOp;
        int[] result = new int[resultList.size()];
        int index = 0;
        for (Integer op : finalOp) {
            result[index++] = op;
        }
        return result;

    }
}
