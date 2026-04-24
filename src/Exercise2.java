public class Exercise2 {
    private int value;
    private boolean bChanged = false;

    public synchronized void set(int value) {
        this.value = value;
        bChanged = true;

        System.out.println("Producer set value: " + value);

        notify();
    }

    public synchronized int get() {
        while (!bChanged) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer interrupted");
            }
        }

        bChanged = false;

        System.out.println("Consumer got value: " + value);

        return value;
    }
}
