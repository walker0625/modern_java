package concurrent;

import com.sun.webkit.ThemeClient;

public class ThreadTest {

    public static void main(String[] args) {

        NewThread newThread = new NewThread();
        newThread.run();

        // 먼저 찍히는 경우가 생기 Thread는 순서가 정해져 있지 않음
        System.out.println("main hello");
    }


    static class NewThread extends Thread {

        @Override
        public void run() {
            System.out.println("Thread : " + Thread.currentThread().getName());
        }
    }

}
