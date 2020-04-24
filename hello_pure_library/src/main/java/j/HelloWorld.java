package j;

public class HelloWorld {
    public native void print();

    public native String stringFromJNI();

    static {
        System.loadLibrary("hello");
    }
}