package ticket;

public class Client {
    public static void main(String[] args) {
        int amount = 10;
        TicketSystem ticketSystem = new TicketSystem(amount);

        new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                ticketSystem.sell();
            }
        }, "A 售票点").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                ticketSystem.sell();
            }
        }, "B 售票点").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ticketSystem.sell();
            }
        }, "C 售票点").start();
    }
}
