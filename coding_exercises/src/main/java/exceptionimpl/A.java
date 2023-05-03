package exceptionimpl;

public class A {

    public void check() throws Exception{
       try{
           int i = 20;
           int p = i / 0;
       }catch (ArithmeticException ar) {
           throw new RuntimeException("test exc in class a with comma removed Arithmatic " , ar);
       }
    }
}
