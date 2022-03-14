package concurrent;

public class ThreadTest3 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {

            while(true) {
                try {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("Thread1");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });

        Thread thread2 =new Thread(() -> {
            for(int i = 0; i < 7; i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    System.out.println("interrupt!");
                    e.printStackTrace();
                }
                System.out.println("Thread2");
            }
        });

        Thread thread3 = new Thread(() ->{
            for(int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread3");
            }
        });
        //thread1.run(); run()은 새로운 쓰레드를 만드는 것이 아니라, 기존의 쓰레드(main)를 동작시키는 것 일뿐

        thread1.start();
        thread1.interrupt();
        thread2.start();
        thread3.start();

       /* try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        thread2.interrupt();
    }
}
