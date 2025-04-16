package jdk8.collection;

import jdk8.utils.ComparatorUtil;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetCompare001 {

    private static final Comparator<Integer> INTEGER_COMPARATOR = ComparatorUtil.getComparator(true);

    public static void main(String[] args) {
        // Approach 1
        // TreeSet<Integer> treeSet = new TreeSet<>();

        // Approach 2
        TreeSet<Integer> treeSet = new TreeSet<>(INTEGER_COMPARATOR);

        treeSet.add(10);
        treeSet.add(20);
        treeSet.add(13);
        treeSet.add(21);
        treeSet.add(44);
        treeSet.add(33);
        treeSet.add(45);
        treeSet.add(55);
        treeSet.add(16);
        treeSet.add(22);
        treeSet.add(33);
        treeSet.add(45);

        System.out.println("Tree set values: ");
        treeSet.forEach(element -> System.out.print(element + " "));

//        Approach 1 ( sorting at the last)
//        System.out.println("\n Executing comparator...");
//        treeSet.stream().sorted(INTEGER_COMPARATOR).forEach(element -> System.out.print(element + " "));

    }
}
