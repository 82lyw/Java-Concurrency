package base;

public class RunnableTask implements Runnable{
    @Override
    public void run() {
        System.out.println("thread created by implements runnable way");
    }
}
