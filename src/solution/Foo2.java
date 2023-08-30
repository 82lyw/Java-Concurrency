package solution;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Foo2 {
    private boolean firstFinished;
    private boolean secondFinished;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Foo2() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();

        try{
            printFirst.run();
            firstFinished=true;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();

        try{
            while(!firstFinished) {
                condition.await();
            }
            printSecond.run();
            secondFinished=true;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();

        try{
            while(!secondFinished) {
                condition.await();
            }
            printThird.run();
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Foo2 foo2 = new Foo2();

        new Thread(()->{
            try {
                foo2.first(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("first");
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"A").start();

        new Thread(()->{
            try {
                foo2.second(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("second");
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"B").start();


        new Thread(()->{
            try {
                foo2.third(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("third");
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"C").start();

    }
}
