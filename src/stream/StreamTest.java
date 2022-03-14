package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("aaa");
        names.add("bbb");
        names.add("ccc");
        names.add("ddd");
        names.add("eee");

        List<String> upperNames = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        
        // 중계 오퍼레이터(ex : map()) - 1. Stream을 return 2. Lazy함(종료형 오퍼레이터가 없으면 중간에 작성한 중계형 오퍼레이터를 실행하지 않는다.)
        // 종료형 오퍼레이터(ex : collect()) 1. Stream 외의 다른 type을 return
        
        // Stream은 처리하는 원본 데이터를 변경하지 않고 별도의 data 흐름을 생성한다.
        names.forEach(System.out::println); // 원본 데이터는 변하지 않음
        
        // 기존의 방식은 변경된 데이터를 기존 데이터에 갱신하는 것이 복잡하며 원본 데이터를 건드리게 됨
        for(int i = 0; i < names.size(); i++) {
            names.set(i, names.get(i).toUpperCase());
        }

        upperNames.forEach(System.out::println);

        // 간단하게 병렬처리(복수의 thread 이용)가 가능하다.(parallelStream())
        // 단 thread 생성 및 데이터 sync 비용 등을 고려하여 병렬 처리가 필요하며
        // 무조건 병렬 처리가 빠른 것이 아니므로 데이터의 양(대용량일 경우 고려), 처리 함수의 종류 등을 고려하여
        // stream()과 parallelStream()을 비교하여 성능을 테스트 후 사용
        List<String> upperNames2 = names.parallelStream().map((s) -> {
            System.out.println(s + " : " + Thread.currentThread().getName());
            return s.toLowerCase();
        }).collect(Collectors.toList());

        upperNames2.forEach(System.out::println);
    }

}
