package misc;

import java.util.concurrent.*;

public class CyclicBarrierEx {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ecs = Executors.newFixedThreadPool(10);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        ecs.submit(new CyclicTask(cyclicBarrier,1));
        ecs.submit(new CyclicTask(cyclicBarrier,2));
        ecs.submit(new CyclicTask(cyclicBarrier,3));

        Thread.sleep(10000);
        System.out.println("Completing main");

        ecs.shutdown();
    }
}

class CyclicTask implements  Runnable{
    public final CyclicBarrier cyclicBarrier;
    private final int timeToSleep;

    CyclicTask(CyclicBarrier cyclicBarrier, int sleep) {
        this.cyclicBarrier = cyclicBarrier;
        this.timeToSleep = sleep;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Statring execution");
                TimeUnit.SECONDS.sleep(timeToSleep);
                System.out.println("Sleeping completed for "
                        +timeToSleep+", name:: "+Thread.currentThread().getName());
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
