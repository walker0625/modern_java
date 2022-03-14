package lambda;

public class Greeting {

    String name;

    public Greeting() {};

    public Greeting(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    static void hi(String name) {
        System.out.println("hi " + name);
    }

    void hello(String name) {
        System.out.println("hello " + name);
    }

    static String returnHiString(String name) {
        return "hi " + name;
    }

    String returnHelloString(String name) {
        return "hello " + name;
    }

}
