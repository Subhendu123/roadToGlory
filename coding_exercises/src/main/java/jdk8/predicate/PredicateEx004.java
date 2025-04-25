package jdk8.predicate;

import java.util.function.Predicate;

public class PredicateEx004 {

    public static void main(String[] args) {
        Predicate<Integer> PRED_POS_INT = i -> i > 0;

        Predicate<Integer> PRED_EVEN_INT = i -> i % 2 == 0;


        Predicate<Integer> COMBPRED_ADD = PRED_POS_INT.and(PRED_EVEN_INT);
        Predicate<Integer> COMBPRED_OR = PRED_POS_INT.or(PRED_EVEN_INT);

        System.out.println("Test result for combined predicate (both even and greater than 0) for the integer value of 10: " + COMBPRED_ADD.test(10));
        // todo COMBPRED_ADD.test(5) can be written as the following
        System.out.println("Test result for combined predicate (both even and greater than 0) for the integer value of 5: " + PRED_EVEN_INT.and(PRED_POS_INT).test(5));

        System.out.println("NEG Test result for combined predicate (both even and greater than 0) for the integer value of 10: " + COMBPRED_ADD.negate().test(10));
        System.out.println("NEG Test result for combined predicate (both even and greater than 0) for the integer value of 5: " + COMBPRED_ADD.negate().test(5));

        System.out.println("Test result for combined predicate (both even or greater than 0) for the integer value of 5: " + COMBPRED_OR.test(5));
        System.out.println("Test result for combined predicate (both even or greater than 0) for the integer value of -15: " + COMBPRED_OR.test(-15));

        System.out.println("Test result for combined predicate (both even or greater than 0) for the integer value of -10: " + COMBPRED_OR.test(-10));
    }
}
