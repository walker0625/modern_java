package concurrent;

import java.util.concurrent.*;

public class ExecuteTest3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("inner working");
                Thread.sleep(2000);
                return "hello";
            }
        };

        Callable hello2 = () -> {
            Thread.sleep(2000);
            return "hello2";
        };

        Future<String> futureTxt = executorService.submit(hello2);
        System.out.println("start");

        System.out.println(futureTxt.isDone());

        String getTxt = futureTxt.get(); // get()이 끝날때까지 아래 동작은 기다림(블락킹 콜)
        System.out.println(getTxt);

        System.out.println(futureTxt.isDone());

        System.out.println("end");

        ////////////////////////////////////////////////////////////////

        System.out.println("-----------------------test------------------------");

        Future<String> futureTxt2 = executorService.submit(hello);

        // 둘다 get은 불가함
        futureTxt2.cancel(false); // 실행은 기다림
        futureTxt2.cancel(true); // 실행도 멈춤
        //System.out.println(futureTxt2.get()); // cancel 했기 때문에 에러 발생함

        System.out.println(futureTxt2.isDone()); // 값을 가져올 수 있다가 아니라 단지 끝났다는 의미

        executorService.shutdown();
    }

}
