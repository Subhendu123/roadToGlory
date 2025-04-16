package jdk8.lambdaexp;

//@FunctionalInterface
// if we uncomment above line, we will get an error saying:
//  Unexpected @FunctionalInterface annotation
//  jdk8.lambdaexp.FIExp002 is not a functional interface
public interface FIExp002 {

 default void foo() {}
}
