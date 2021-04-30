package producerconsumer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Print1to100 {

    public static void main(String[] args) {
        Print1to100 o = new Print1to100();
        o.printNumbers();
    }

    private void printNumbers(){
        System.out.println("Printing 1 to 100");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("------CYCLE DONE ------------");
        });

        IntStream.rangeClosed(0, 9)
                .boxed()
                .map(i -> new PrintSequenceTask(i, atomicInteger, cyclicBarrier))
                .map(p -> executorService.submit(p))
                .collect(Collectors.toList());

        executorService.shutdown();

    }
}

class PrintSequenceTask implements  Runnable{
    private final int index;
    private final AtomicInteger atomicInteger;
    private final CyclicBarrier cyclicBarrier;

    PrintSequenceTask(int index, AtomicInteger atomicInteger, CyclicBarrier cyclicBarrier) {
        this.index = index;
        this.atomicInteger = atomicInteger;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run(){
        for(int i =1; i<= 10; i++){
            while((atomicInteger.get()-index-1)%10 !=0);
            System.out.println(atomicInteger.get() + " " +Thread.currentThread().getName());
            atomicInteger.incrementAndGet();
            //await();
        }
    }

    private void await() {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

