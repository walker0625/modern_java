package concurrent;

import java.util.concurrent.*;

public class ExecutorTest2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(getRunnable("test1"));
        executorService.submit(getRunnable("test2"));
        executorService.submit(getRunnable("test3"));

        executorService.shutdown();

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(getRunnable("delay 3 seconds") , 3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("print every 2 SECONDS"), 2, 1, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown(); // 2초 반복 딜레이는 기다리지 않고 종료
        // scheduledExecutorService.shutdownNow(); 모든 딜레이를 기다리지 않고 종료해버림



    }

    // Runnable은 기본적으로 void(실행의 결과를 가져올 수가 없음)
    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
