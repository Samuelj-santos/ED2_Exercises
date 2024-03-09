package GenericQueue;

public class Main {
    public static void main(String[] args) {
        LDEQueue<Integer> queue = new LDEQueue<Integer>();
                queue.enQueue(10);
                queue.enQueue(20);
                queue.enQueue(30);
                queue.enQueue(40);
                queue.deQueue();

    }
}
