package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReference {

    public static void main(String[] args) {

        // :: 다른 인스턴스의 메소드를 참조해서 사용하겠다는 의미

        // static 메소드 사용(인스턴스 생성 필요없음)
        Function<String, String> returnHiString = Greeting::returnHiString;
        System.out.println(returnHiString.apply("minwoo"));

        UnaryOperator<String> returnHiString2 = Greeting::returnHiString;
        System.out.println(returnHiString2.apply("minwoo"));

        Consumer<String> hiAction = Greeting::hi;
        hiAction.accept("minwoo");


        // 인스턴스 메소드를 사용할 경우
        Greeting greeting = new Greeting();

        Function<String, String> returnHelloString = greeting::returnHelloString;
        System.out.println(returnHelloString.apply("minwoo"));

        UnaryOperator<String> returnHelloString2 = greeting::returnHelloString;
        System.out.println(returnHelloString2.apply("minwoo"));

        Consumer<String> helloAction = greeting::hello;
        helloAction.accept("minwoo");


        // 기본 생성자(no param)로 만들 때
        Supplier<Greeting> defaultGreeting = Greeting::new;
        Greeting greetingInstance = defaultGreeting.get(); // get()을 해줘야 객체가 생성됨
        greetingInstance.hi("minwoo");


        // 초기값이 있는 생성자(param)로 만들 때
        Function<String, Greeting> paramGreeting = Greeting::new;
        Greeting paramInstance = paramGreeting.apply("minwoo");
        System.out.println(paramInstance.getName());


        // 임의 객체의 인스턴스 메소드 참조
        String[] names = {"bbb", "ccc", "aaa"};

        // @FunctionalInterface
        Arrays.sort(names, (s1, s2) -> 0);

        // 임의의 객체(String)의 인스턴스 메소드인 compareToIgnoreCase 메소드(static이 아님)를 참조하여 사용
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names)); // [aaa, bbb, ccc]
    }

}
