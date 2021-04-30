package producerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintOddEven {

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock reentrantLock = new ReentrantLock(true);
        final Condition evenCondition = reentrantLock.newCondition();
        final Condition oddCondition = reentrantLock.newCondition();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger atomicInteger = new AtomicInteger(1);

        executorService.submit(new PrintTask(evenCondition, oddCondition, 0, atomicInteger, reentrantLock, 100));
        executorService.submit(new PrintTask(oddCondition, evenCondition, 1, atomicInteger, reentrantLock, 100));
        Thread.sleep(100000);
        executorService.shutdown();

    }
}


class PrintTask implements Runnable {

    private final Condition awaitCondition;
    private final Condition notifyCondition;
    private final int result;
    private final AtomicInteger atomicInteger;
    private final Lock lock;
    private final int max;

    PrintTask(Condition awaitCondition, Condition notifyCondition, int result, AtomicInteger atomicInteger, Lock lock, int max) {
        this.awaitCondition = awaitCondition;
        this.notifyCondition = notifyCondition;
        this.result = result;
        this.atomicInteger = atomicInteger;
        this.lock = lock;
        this.max = max;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            while (atomicInteger.get() < max) {
                while (atomicInteger.get() % 2 != result) {
                    awaitCondition.await();
                }
                System.out.println(atomicInteger.get() + " " + Thread.currentThread().getName());
                atomicInteger.incrementAndGet();
                notifyCondition.signalAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
