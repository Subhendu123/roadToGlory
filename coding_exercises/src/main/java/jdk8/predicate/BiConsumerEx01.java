package jdk8.predicate;

import java.util.function.BiConsumer;

public class BiConsumerEx01 {

    public static void main(String[] args) {
        BiConsumer<String, String> concatString = (s1,s2 )-> System.out.println(s1.concat(s2));

        concatString.accept("Subhendu", "Das");
    }
}
