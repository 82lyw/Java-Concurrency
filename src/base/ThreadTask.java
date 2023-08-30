package base;

public class ThreadTask extends Thread{
    @Override
    public void run() {
        System.out.println("thread created by extends thread way");
    }
}
