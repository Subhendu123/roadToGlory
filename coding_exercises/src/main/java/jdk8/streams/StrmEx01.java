package jdk8.streams;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StrmEx01 {
    public static void main(String[] args) {
        ArrayList<Integer> numList = new ArrayList<>();
        for(int i = 10; i < 100; i=i+7) {
            numList.add(i);
        }
        System.out.println("numList: " + numList);

        System.out.println("Number list for even numbers: ");
        numList.stream().filter(num -> num % 2 == 0).collect(Collectors.toList()).forEach(num -> System.out.print(num + " , "));
        System.out.println();

        System.out.println("Number list for odd numbers: ");
        numList.stream().filter(num -> num % 2 == 1).toList().forEach(num -> System.out.print(num + " , "));

        System.out.println("Map example using streams by mul by 2");
        numList.stream().map(num -> num * 2).toList().forEach(num -> System.out.print(num + " , "));
    }
}
