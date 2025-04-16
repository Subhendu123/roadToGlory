package jdk8.lambdaexp;

import org.apache.log4j.LogManager;

public class LambdaExp001 {

    public static void main(String[] args) {

        FIExp003 f3 = (a ,b ) -> {
            System.out.println("Adding two values a= "+a+" and b= "+b);
            System.out.println("The sum is "+a+b);
        };

        f3.add(10, 20);

        FIExp001 fi = () -> {
            System.out.println("implemented.");
        };

        fi.implementMe();

        FIExp004 f4 = s -> s.length();
        System.out.println("The length of Hello World: "+f4.getLength("Hello World"));

    }

}
