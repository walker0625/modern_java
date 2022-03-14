package concurrent;

import java.util.Locale;
import java.util.concurrent.*;

public class CompletableFutureTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() ->{
            System.out.println("Return : " + Thread.currentThread().getName());
            return "test1";
        }).thenApply(txt -> {
            System.out.println("ThenApply : " + Thread.currentThread().getName());
            return txt.toUpperCase();
        });

        System.out.println(completableFuture.get()); // get()을 해야 실행됨

        CompletableFuture<Void> completableFuture2 = CompletableFuture.supplyAsync(() ->{
            System.out.println("Void : " + Thread.currentThread().getName());
            return "test2";
        }).thenAccept(s -> System.out.println(s.toUpperCase()))
                .thenRun(() -> {
                    System.out.println("thenRun : " + Thread.currentThread().getName());
                });

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture.runAsync(() -> {
            System.out.println("Executors : " + Thread.currentThread().getName());
        }, executorService); // 생성한 쓰레드를 사용하고 싶을때

        executorService.shutdown();
    }

}
