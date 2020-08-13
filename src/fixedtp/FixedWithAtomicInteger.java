package fixedtp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Instead of making main thread to wait, we can create atomic integer then
 * keep on checking whether task execution completed or not
 */
public class FixedWithAtomicInteger {
    public static void main(String[] args) {
        new FixedWithAtomicInteger().main();
    }

    private void main() {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        ExecutorService ecs = Executors.newFixedThreadPool(10);
        for(int i=0;i<100;i++){
            ecs.execute(new Task(atomicInteger));
        }
        ecs.shutdown();
        while(atomicInteger.get()!=0);


        System.out.println("complted main thread");
    }

    class Task implements Runnable{
        final AtomicInteger counter;

        public Task(AtomicInteger atomicInteger){
            this.counter = atomicInteger;
        }


        @Override
        public void run() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            counter.decrementAndGet();
        }
    }
}
