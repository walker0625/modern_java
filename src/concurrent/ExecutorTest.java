package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

    public static void main(String[] args) {
        // 일련의 Thread의 작업의 생성과 실행들을 고수준으로 맡기는 기능(일일히 관리가 어렵기 때문에)
        // 작업이 끝나도 다음 작업을 대기하기 때문에 shutdown()이 필요
        // (graceful - 현재 진행중인 작업은 끝까지 마치고 종료) shutdownNow()는 무조건 종료
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName());
            }
        });

        executorService.submit(() -> {
            System.out.println("Thread : "  + Thread.currentThread().getName());
        });

        executorService.shutdown();
    }

}
