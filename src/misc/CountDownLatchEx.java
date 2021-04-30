package misc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchEx {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ecs = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        ecs.submit(new Task(countDownLatch));
        ecs.submit(new Task(countDownLatch));
        ecs.submit(new Task(countDownLatch));

        /**
         * Here main thread block unitl latch count becomes zero
         */
        countDownLatch.await();
        System.out.println("Completing main");

        ecs.shutdown();
    }
}

class Task implements  Runnable{
    public final CountDownLatch countDownLatch;

    Task(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());
    }
}
