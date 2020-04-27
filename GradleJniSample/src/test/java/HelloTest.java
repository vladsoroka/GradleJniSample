import org.junit.Test;

import j.HelloWorld;

public class HelloTest {
    @Test
    public void testHello() {
        System.out.print("Show JNI info : " + new HelloWorld().stringFromJNI());
    }
}
