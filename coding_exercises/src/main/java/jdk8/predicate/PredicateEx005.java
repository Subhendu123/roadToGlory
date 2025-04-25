package jdk8.predicate;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PredicateEx005 {

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("John");
        names.add("Jane");
        names.add("Jack");
        names.add("Jill");
        names.add("James");
        names.add("Rohit");
        names.add("Bob");
        names.add("Betty");
        names.add("Bruce");
        names.add("David");

        Predicate<String> namesWithJ = name -> name.startsWith("J");

        System.out.println("Names which starts with J:");
        names.stream().filter(namesWithJ).forEach(System.out::println);

        System.out.println("Names which starts with J (legacy approach):");
        for(String name : names){
            if(namesWithJ.test(name)){
                System.out.println(name);
            }
        }
    }
}
