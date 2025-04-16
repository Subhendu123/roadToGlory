package jdk8.lambdaexp;

public class AnnInnKlass001 {

    public static void main(String[] args) {

        Runnable runnable = () -> { for(int i=0;i<10;i++) System.out.println(Thread.currentThread().getName() + " Thread Running " + i);};
        Thread thread = new Thread(runnable);
        thread.start();
        for (int i=0;i<10;i++) System.out.println(Thread.currentThread().getName() + " Thread Running " + i);
    }
}
