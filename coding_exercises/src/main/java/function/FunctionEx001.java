package function;

import java.util.function.Function;

public class FunctionEx001 {

    public static void main(String[] args) {
        Function<String, Integer> function = s -> {
            if(s!= null)
                return s.length();
            throw new NullPointerException("Null Value cannot be passed.");
        };

        System.out.println(function.apply("123"));
        try{
        System.out.println(function.apply(null));}
        catch(NullPointerException e){
            System.out.println(e);
        }


        Function<String, Integer> noOfSpacesFunction = s -> {
            if(s!= null){
                int l1 = s.length();
                int l2 = s.replaceAll(" ", "").length();
                return l1 - l2;
//               int len = s.split(" ").length;
//               return s.endsWith(" ") ? len : len - 1;
            }
            throw new NullPointerException("Null Value cannot be passed.");
        };
        System.out.println(noOfSpacesFunction.apply("123 asddas asdad as asdasd asd"));
        System.out.println(noOfSpacesFunction.apply("123 asddas asdad as asdasd asd  "));




     }
}
