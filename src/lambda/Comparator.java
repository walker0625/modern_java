package lambda;

import java.util.ArrayList;
import java.util.List;

public class Comparator {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("atest1");
        names.add("btest2");
        names.add("ctest3");
        names.add("dtest4");
        names.add("etest5");

        java.util.Comparator<String> comp = String::compareToIgnoreCase;
        names.sort(comp.reversed());
        names.sort(String::compareToIgnoreCase);

        names.forEach(System.out::println);

        System.out.println("----------------");

        names.sort(String::compareToIgnoreCase);

        names.forEach(System.out::println);

        names.removeIf(s -> s.startsWith("t"));

        System.out.println("----------------");

        names.forEach(System.out::println);
    }

}
