package jdk8.consumer;

import java.util.Date;
import java.util.function.Supplier;

public class Supplier01 {

    public static void main(String[] args) {
        Supplier<Date> getDate = () -> new Date();
        System.out.println(getDate.get());
    }
}
