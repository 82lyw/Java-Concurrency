package ticket;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TicketSystem {
    private int amount;

    private final ReentrantLock lock = new ReentrantLock(true);

    public TicketSystem(int amount) {
        this.amount = amount;
    }

    /**
     * 1.sell()方法加关键字synchronized
     * 2.尽量减小锁粒度：只对售票操作加锁
     * 3.ReentrantLock
     */
    public void sell() {
        if (amount <= 0) {
            throw new RuntimeException("没有票了！");
        }
        // TimeUnit.SECONDS.sleep(1); 表示方法在这里暂停 1 秒钟，这里用于模拟卖票的过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // this指代调用该方法的对象
//        synchronized (this) {
//            amount--;
//            System.out.println(Thread.currentThread().getName() + "，剩余票数 => " + amount);
//        }

        lock.lock();
        try{
            amount--;
            System.out.println(Thread.currentThread().getName() + "，剩余票数 => " + amount);
        }finally {
            lock.unlock();
        }
    }
}
