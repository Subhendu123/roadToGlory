/// *
// * Copyright (c) 2026.
// * This is created and managed by $(git config user.name)
// */
//
//package com.roadtoglory.ds.graphs;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///*
//*
//*
//
//This class CloneGraph is created and managed by subhe
//Created on 03-02-2026 at 19:19 for the project Leetcode 133. Clone Graph (Med)
//
//*
//*
//*/
//class Node {
//    public int val;
//    public List<Node> neighbors;
//
//    public Node() {
//        val = 0;
//        neighbors = new ArrayList<Node>();
//    }
//
//    public Node(int _val) {
//        val = _val;
//        neighbors = new ArrayList<Node>();
//    }
//
//    public Node(int _val, ArrayList<Node> _neighbors) {
//        val = _val;
//        neighbors = _neighbors;
//    }
//
//    @Override
//    public String toString() {
//        return "Node{" +
//                "val=" + val +
//                ", neighbors=" + neighbors +
//                '}';
//    }
//}
//
//public class CloneGraph {
//    Map<Node, Node> clonedMap = new HashMap<>();
//
//    public static void main(String[] args) {
//        Node node = new Node(1);
//        for (int i = 2; i < 4; i++) {
//            node.neighbors.add(i, new Node(i));
//        }
//
//        System.out.println(node);
//
//    }
//
//    public Node cloneGraph(Node node) {
//
//        Node result = new Node();
//        if (node == null) {
//            return null;
//        }
//
//        clonedMap.put(node, node);
//
//    }
//
//}
