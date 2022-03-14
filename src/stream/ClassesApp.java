package stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassesApp {

    public static void main(String[] args) {
        List<Classes> springClasses = new ArrayList<>();
        springClasses.add(new Classes(1, "spring boot", true));
        springClasses.add(new Classes(2, "spring jpa", true));
        springClasses.add(new Classes(3, "spring mvc", false));
        springClasses.add(new Classes(4, "spring core", false));
        springClasses.add(new Classes(5, "rest api", false));

        List<Classes> javaClasses = new ArrayList<>();
        javaClasses.add(new Classes(6, "java test", true));
        javaClasses.add(new Classes(7, "java modern", true));
        javaClasses.add(new Classes(8, "java 8 to 11", true));

        List<List<Classes>> classList = new ArrayList<>();
        classList.add(springClasses);
        classList.add(javaClasses);

        System.out.println("<Spring 수업중 수업명이 spring으로 시작하는 수업>");

        springClasses.stream().filter(c -> c.getTitle().startsWith("spring"))
                              .forEach(c -> System.out.println("수업번호 : " + c.getId()));

        System.out.println("-------------------------");

        System.out.println("<Spring 수업중 open되어 있는 수업>");

        springClasses.stream().filter(c -> !c.isClosed()) // close되지 않은 수업이므로 !이 맞음
                              .forEach(c -> System.out.println(c.getTitle()));

        //springClasses.stream().filter(Predicate.not(Classes::isClosed))
        //                      .forEach(c -> System.out.println(c.getTitle()));

        System.out.println("-------------------------");

        System.out.println("<전체 수업중 open되어 있는 수업>");

        // flatMap() 하나의의 list를 별의 요소로 확대
        classList.stream().flatMap(Collection::stream) // list -> list.stream()
                          .filter(c -> !c.isClosed())
                          .forEach(c -> System.out.println(c.getTitle()));

        System.out.println("-------------------------");

        System.out.println("<Spring 수업중 수업이름만 추출하여 스트림 만들기>");

        springClasses.stream().map(c -> c.getTitle()) // Classes::getTitle
                              .forEach(s -> System.out.println(s)); // System.out::println

        System.out.println("-------------------------");

        System.out.println("<전체 수업의 수업이름을 하나의 스트림으로 만들기>");

        classList.stream().flatMap(Collection::stream) // list -> list.stream()
                          .forEach(c -> System.out.println(c.getTitle()));

        System.out.println("-------------------------");

        System.out.println("<10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개만 출력>");

        Stream.iterate(10, i -> i + 1)
                .skip(10).limit(10)
                .forEach(System.out::println);

        System.out.println("-------------------------");

        System.out.println("<Java 수업 중에 test가 들어있는 수업이 있는지 확인>");

        javaClasses.stream().filter(c -> c.getTitle().contains("test")).forEach(c -> System.out.println(c.getTitle()));
        javaClasses.stream().anyMatch(c -> c.getTitle().contains("test")); // anyMatch는 종결형으로 boolean을 return

        System.out.println("-------------------------");

        System.out.println("<Spring 수업 중에 spring이 들어있는 수업의 제목만 모아서 list로 만들기>");

        List<String> containSpringClasses = springClasses.stream().filter(c -> c.getTitle().contains("spring")).map(Classes::getTitle)
                                                                   .collect(Collectors.toList());
        containSpringClasses.forEach(System.out::println);
    }

}
