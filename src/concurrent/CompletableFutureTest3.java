package concurrent;

import java.sql.PreparedStatement;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello : " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World : " + Thread.currentThread().getName());
            return "World";
        });

        // 둘간의 의존성이 없는 작업일 경우
        CompletableFuture<String> combinedFuture = hello.thenCombine(world, (h, w) -> h + " "+ w);
        System.out.println(combinedFuture.get());

        // 둘중에 먼저 오는 것
        CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
    }
}
