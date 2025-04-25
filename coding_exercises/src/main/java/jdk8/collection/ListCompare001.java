package jdk8.collection;

//import jdk8.utils.ComparatorUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ListCompare001 {

//    private static final Comparator<Integer> INTEGER_COMPARATOR =ComparatorUtil.getComparator();

    public static void main(String[] args) {

        List<Integer> l1 = Arrays.asList(21,24,32,14,53,36,73,18,91);
//        l1.sort(INTEGER_COMPARATOR);
        l1.forEach(System.out::println);
    }
}
