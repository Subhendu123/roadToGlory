package jdk8.predicate;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PredicateEx003 {

    public static void main(String[] args) {
        Predicate<ArrayList<Integer>> PRED_POS_INT = i -> i.size() > 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        ArrayList<Integer> emptyList = new ArrayList<>();
        System.out.println("Test result PRED_POS_INT.test(list): " + PRED_POS_INT.test(list));
        System.out.println("Test PRED_POS_INT.test(emptyList): " + PRED_POS_INT.test(emptyList));
    }
}