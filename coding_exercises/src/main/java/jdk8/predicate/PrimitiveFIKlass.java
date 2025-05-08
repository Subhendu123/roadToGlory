package jdk8.predicate;

import java.util.function.BinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.UnaryOperator;

public class PrimitiveFIKlass {
    public static void main(String[] args) {
        DoublePredicate doublePredicate = x -> x > 5;
        System.out.println("doublePredicate.test(50) " + doublePredicate.test(50));
        System.out.println("doublePredicate.test(5) " + doublePredicate.test(5));

        DoubleFunction<Double> doubleFunction = x -> x * 5;
        System.out.println("doubleFunction.apply(50): " + doubleFunction.apply(50));

        UnaryOperator<Integer> multiply = x -> x * x;
        System.out.println("multiply.apply(50): " + multiply.apply(50));

        BinaryOperator<String> concatBinString = (s1, s2) -> s1.concat(s2);
        System.out.println(concatBinString.apply("Subhendu", "Das"));
    }
}
