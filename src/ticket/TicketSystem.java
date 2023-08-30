package ticket;

import java.util.concurrent.TimeUnit;

public class TicketSystem {
    private int amount;

    public TicketSystem(int amount) {
        this.amount = amount;
    }

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
        amount--;
        System.out.println(Thread.currentThread().getName() + "，剩余票数 => " + amount);
    }
}
