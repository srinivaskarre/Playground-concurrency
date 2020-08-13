package syncproblem;

public class CounterWithThreads {

    volatile int value = 0;
    public static void main(String[] args) throws InterruptedException {
        new CounterWithThreads().main();
    }

    private void main() throws InterruptedException {
        int i=0;
        while(i++<100) {
            Thread t0 = new Thread(new Task());
            Thread t1 = new Thread(new Task());
            Thread t2 = new Thread(new Task());
            t0.start();
            t1.start();
            t2.start();
        }
        Thread.sleep(5000);
        System.out.println(value);
    }

    class Task implements Runnable{

        @Override
        public void run() {
            value++;
            value++;
            value--;
            System.out.println("done updating");
        }
    }
}
