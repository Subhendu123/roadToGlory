package exceptionimpl;

public class B {

    public void check2() throws Exception{
       try{
          new A().check();
       }catch (RuntimeException r) {
           throw new RuntimeException("test exc in class B with plus ", r);
       }
    }
}
