package jdk8.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StrmEx02 {
    public static void main(String[] args) {
       List<String> strList = new ArrayList<>();
       strList.add("Alice");
       strList.add("Paul");
       strList.add("Bob");
       strList.add("Carl");
       strList.add("Dan");
       strList.add("Jack");
       strList.add("John");
       strList.add("Mary");
       strList.add("Tom");

       List<String> convertedStrList = strList.stream().filter(s -> s.startsWith("J")).map(s -> s.toUpperCase()).toList();
       convertedStrList.forEach(System.out::println);
       // JACK JOHN are the output


    }
}
