//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //Test for Exercise 2
    public static void main(String[] args) {
        Exercise2 resource = new Exercise2();

        Thread consumer = new Thread(() -> {
            int result = resource.get();
            System.out.println("Final consumed value: " + result);
        });

        Thread producer = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            resource.set(100);
        });

        consumer.start();
        producer.start();
    }
}