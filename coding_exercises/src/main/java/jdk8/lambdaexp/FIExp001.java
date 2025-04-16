package jdk8.lambdaexp;

@FunctionalInterface
public interface FIExp001 {

    public void implementMe();

    default void execute(){
        System.out.println("Excute the default method which is introduced in jdk 8");
    }

    public static void run(){
        System.out.println("This is an example of the static method in the Interface.");
    }
}
