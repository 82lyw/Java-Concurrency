package base;

public class Solution {
    public static void main (String[] args) {

        ThreadTask threadTask = new ThreadTask();
        threadTask.start();

        Thread runnableTask = new Thread(new RunnableTask());
        runnableTask.start();

        System.out.println("main thread");



        // test join():主线程等待子线程执行完毕再执行
        Thread thread = new Thread(()->{
            for(int i=0;i<10;i++) {
                System.out.println("线程名=>"+Thread.currentThread().getName()+",i="+i);
            }
        });
        thread.start();

        for(int i=0;i<20;i++) {
            System.out.println("线程名=>"+Thread.currentThread().getName()+",i="+i);
            if(i==3) {
                try {
                    System.out.println("thread.join();开始");
                    thread.join();
                    System.out.println("thread.join();结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("end");
    }
}
