package jdk8.predicate;

import java.util.function.BiPredicate;

public class BiPredicateEx01 {

    public static void main(String[] args) {

        BiPredicate<String, Integer> biPredicate = (s1, s2) -> s1 != null && s2 != null ? s1.equals(s2.toString()) : false;

        System.out.println(biPredicate.test("1", 1));

        BiPredicate<Integer, Integer> sumEvenCalculation = (i1, i2) -> (i1+i2) % 2 == 0;
        System.out.println(sumEvenCalculation.test(1, 2));
        System.out.println(sumEvenCalculation.test(11, 21));

    }
}
