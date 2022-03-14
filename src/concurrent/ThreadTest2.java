package concurrent;

public class ThreadTest2 {

    public static void main(String[] args) {

        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });*/

        Thread thread = new Thread(() -> {
            while(true) {
                System.out.println("Thread : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { // 자고 있는 쓰레드를 깨우면 발생
                    // 에러가 발생하면 무한루프가 종료되고 해당 쓰레드 종료
                    System.out.println("interrupt!");
                    return; // run의 리턴타입은 void라 그냥 return만 하면 종료
                }
            }
        });

        thread.start();

        System.out.println("Main : " + Thread.currentThread().getName());

        try {
            thread.join(); // Thread 의 종료를 기다림 > interrupt가 발생하지 못함
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread is finished");

        thread.interrupt(); // 종료 시키는 것이 아니라 InterruptedException을 발생시키는 것 뿐

        // 이런 식으로 쓰레드를 일일히 직접 Code로 관리하는 것은 사실상 불가능(쓰레드가 10개, 100개...)
        // 고로 등장하게 된 것이 Executors > Future > CompletableFuture
    }

}
