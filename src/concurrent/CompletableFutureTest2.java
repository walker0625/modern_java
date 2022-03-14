package concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
            return  "Hello";
        });

        // 둘간의 의존성(선후)이 있는 경우에 비동기 실행행
       CompletableFuture<String> future = hello.thenCompose(CompletableFutureTest2::getWorld);

        System.out.println(future.get());
    }
    
    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World : " + Thread.currentThread().getName());
            return message + " World";
        });
    }

}
