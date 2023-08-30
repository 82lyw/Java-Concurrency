package base;

import java.util.concurrent.TimeUnit;

public class DaemonTask {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(()-> {
            for(int i=0;i<10;i++) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程名=>"+Thread.currentThread().getName() +",i="+i);
            }
        });
        myThread.setDaemon(true);
        myThread.start();

        for(int i=0;i<10;i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("线程名=>"+Thread.currentThread().getName() +",i="+i);
        }

        System.out.println(myThread.isDaemon());
        System.out.println("end");
    }
}
