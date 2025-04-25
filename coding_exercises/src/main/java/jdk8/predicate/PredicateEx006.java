package jdk8.predicate;

import com.itextpdf.text.pdf.StringUtils;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PredicateEx006 {

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("John");
        names.add("");
        names.add(null);
        names.add("Jack");
        names.add(null);
        names.add("James");
        names.add("Rohit");
        names.add("");
        names.add("Betty");
        names.add("Bruce");
        names.add("David");

        Predicate<String> notEmptyOrNullName = name -> name != null && !name.isEmpty();

        System.out.println("Names not empty or nullable: :");
        names.stream().filter(notEmptyOrNullName).forEach(System.out::println);

        System.out.println("Names not empty or nullable (legacy approach):");
        for(String name : names){
            if(notEmptyOrNullName.test(name)){
                System.out.println(name);
            }
        }
    }
}
