package solution;

import static java.lang.Thread.sleep;

public class Foo {

    private boolean firstFinished;
    private boolean secondFinished;

    private final Object lock = new Object();

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstFinished = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (!firstFinished) {
                lock.wait();
            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (!secondFinished) {
                lock.wait();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }


    public static void main(String[] args) {
        Foo foo = new Foo();

        new Thread(()->{
            try {
                foo.first(new Runnable() {
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
                foo.second(new Runnable() {
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
                foo.third(new Runnable() {
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
