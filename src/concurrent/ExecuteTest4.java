package concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecuteTest4 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<String> test1 = () -> {
            Thread.sleep(3000);
            return "test1";
        };

        Callable<String> test2 = () -> {
            Thread.sleep(2000);
            return "test2";
        };

        Callable<String> test3 = () -> {
            Thread.sleep(1000);
            return "test3";
        };
        
        // invokeAll()은 모든 Callable을 다 기다림 ex: 내 모든 자산을 읽어옴
        List<Future<String>> testFutureList = executorService.invokeAll(Arrays.asList(test1, test2, test3));

        for(Future<String> fs : testFutureList) {
            System.out.println(fs.get());
        }
        
        // 가장 먼저 가져온 값 하나만 사용 ex:여러 서버에서 동일한 데이터를 가지고 있는데 가장 빨리 가져온 것만 쓰면 될때
        // 싱글 쓰레드(newSingleThreadExecutor())일 경우는 맨위의 callable만 작동함 > 다음 쓰레드가 들어가지 못하므로 가장 빠른 개념이 사라짐
        // newFixedThreadPool(3)을 통해 복수의 쓰레드를 만들어 작업들을 넣어줘야 비교가 가능 > 2개만 만들어도 가장 빠른 test3가 들어가지 못해 실행이 안됨
        String firstTxt = executorService.invokeAny(Arrays.asList(test1, test2, test3));
        System.out.println(firstTxt); // 가장 빨리 나올 수 있는 test3을 가져옴

        executorService.shutdown();
    }

}