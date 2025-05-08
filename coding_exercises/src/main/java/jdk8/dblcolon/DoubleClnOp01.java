package jdk8.dblcolon;

import jdk8.func.interfaces.FuncInterface01;

public class DoubleClnOp01 {

    public static void method2(int a){
        System.out.println("DoubleClnOp01.method2() static Method reference for the method() of FuncInterface01 and input value is "+a);
    }

    public void method3(int a){
        System.out.println("Instance Method reference for the method() of FuncInterface01 and input value is "+a);
    }

    public static void main(String[] args) {

        // new approach with Static Method to achieve the method reference using :: operator
        FuncInterface01 funcInterface012 = DoubleClnOp01::method2;
        funcInterface012.method(10);

        // new approach with Static Method to achieve the method reference using :: operator
        FuncInterface01 funcInterface3 = new DoubleClnOp01()::method3;
        funcInterface3.method(20);

        // old approach of Lambda expression
        FuncInterface01 funcInterface01 = (a) -> System.out.println("Functional Interface example method and input value is "+a);
        funcInterface01.method(5);
    }
}
