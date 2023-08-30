package base;

public class Solution {
    public static void main (String[] args) {

        ThreadTask threadTask = new ThreadTask();
        threadTask.start();

        Thread runnableTask = new Thread(new RunnableTask());
        runnableTask.start();

        System.out.println("main thread");
    }
}
