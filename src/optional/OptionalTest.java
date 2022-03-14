package optional;

import stream.Classes;
import stream.Progress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class OptionalTest {

    public static void main(String[] args) {
        List<Classes> springClasses = new ArrayList<>();
        springClasses.add(new Classes(1, "spring boot", true));
        springClasses.add(new Classes(2, "spring jpa", true));
        springClasses.add(new Classes(3, "spring mvc", false));
        springClasses.add(new Classes(4, "spring core", false));
        springClasses.add(new Classes(5, "rest api", false));

        Optional<Classes> optionalClass = springClasses.stream()
                                                        .filter(c -> c.getTitle().startsWith("spring")).findFirst();

        // isPresent() - isEmpty()는 java11부터 지원하나 !로 쓰면됨
        boolean presentFlag = optionalClass.isPresent();

        // get() - 꺼내는 Optional이 비어있으면 runtimeException 발생 > get 사용은 지양
        String className = optionalClass.get().getTitle();

        // ifPresent() - 비어있지 않을 때만 동작하는 것으로 함(return 타입이 void라 값이 나오지는 않음)
        optionalClass.ifPresent(c -> System.out.println(c.getTitle()));

        // orElse() - 있으면 꺼내고 없으면 인자를 실행하여 return 값을 가져옴(없어도 실행은 됨 - 상수 필요시)
        Classes returnOptionalClass = optionalClass.orElse(createNewClass());

        // orElseGet() - 있으면 인자를 실행하고 싶지 않은 경우(추천 - 동적으로 사용시)
        Classes dontOperClass = optionalClass.orElseGet((Supplier<? extends Classes>) createNewClass());

        // orElseThrow() - 없는 경우 에러를 발생시킴 - 대책이 없는 경우
        Classes throwClass = optionalClass.orElseThrow(IllegalStateException::new);

        // filter() - 있다는 가정하에 걸러냄 없으면 빈 Optional return
        Optional<Classes> filterClass = optionalClass.filter(c -> c.isClosed());

        // map() - Optional이 아닌 타입을 꺼내는 경우
        Optional<Integer> integerClass = optionalClass.map(Classes::getId);

        // flatMap() - Optional 타입을 꺼내는 경우 이중으로 꺼내지 않고 싶으면
        Optional<Optional<Progress>> progress = optionalClass.map(Classes::getProgress);
        Optional<Progress> progress1 = progress.orElseThrow(IllegalStateException::new);
        Progress progress2 = progress1.orElseThrow(IllegalStateException::new);

        Optional<Progress> flatMapProgress = optionalClass.flatMap(Classes::getProgress);
    }

    public static Classes createNewClass() {
        System.out.println("operate class"); // 설사 있더라도 실행된다
       return new Classes(7,"newClass", false);
    }

}
