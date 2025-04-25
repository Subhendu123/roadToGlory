package jdk8.predicate;

import java.util.function.Predicate;

public class PredicateEx001 {

    public static void main(String[] args) {
        Predicate<Integer> PRED_POS_INT = i -> i > 0;

        System.out.println("Test result PRED_POS_INT.test(1): " + PRED_POS_INT.test(1));
        System.out.println("Test PRED_POS_INT.test(-2): " + PRED_POS_INT.test(-2));
        // We will get compile time error for the below line
//        System.out.println("Test result PRED_POS_INT.test(0): " + PRED_POS_INT.test(Integer.valueOf("asas")));
    }
}
