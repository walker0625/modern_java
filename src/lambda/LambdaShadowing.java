package lambda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

// https://vagabond95.me/posts/lambda-with-final 람다가 final만을 사용하는 이유
public class LambdaShadowing {

    public static void main(String[] args) {
        LambdaShadowing lambda = new LambdaShadowing();
        lambda.run();
    }

    private void run() {
        int baseNumber = 10;
        // baseNumber++; 변경을 하는 순간 final이 아님

        // 쉐도잉  : 내부에 선언한 변수로 바깥의 변수를 덮음

        // 로컬 클래스
        class LocalClass {
            void  printBaseNumber() {
                int baseNumber = 11; // 변수 선언을 통해 값을 외부 값을 덮음(쉐도잉이 됨)
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer i) { // 파라미터로 받아온 값으로 외부 값을 덮음(쉐도잉이 됨)
                System.out.println("익명 클래스  : " + i);
            }
        };

        // 람다(람다는 람다를 감싸고 있는 메소드와 같은 범위(scope)임 > 같은 이름으로 변수를 선언 할 수 없음)
        IntConsumer printInt = (i) -> {
            // 변수 선언이 안됨(컴파일 오류 - 쉐도잉이 안됨)
            //baseNumber = 33;
            System.out.println("람다 : " + baseNumber);
        };

        integerConsumer.accept(100);
    }

}