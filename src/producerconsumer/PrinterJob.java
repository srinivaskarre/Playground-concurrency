package producerconsumer;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrinterJob implements Runnable{
    private final PrinterQueue printerQueue;

    public PrinterJob(PrinterQueue printerQueue) {
        this.printerQueue = printerQueue;
    }

    @Override
    public void run() {
        System.out.println("Generating the document");
        printerQueue.printQueue(UUID.randomUUID());
    }
}

class PrinterQueue {
    private final Lock lock = new ReentrantLock();
    public void printQueue(Object document){
        lock.lock();
        try{
            System.out.println("Print the document");
            Thread.sleep(5000);
            System.out.println("Document has been printed, please collect your document :: "+ LocalDateTime.now()
            + " :: id="+document);
            System.out.println("name = "+Thread.currentThread().getName());
        }catch (InterruptedException e){
            System.err.println(e);
        }
        finally {
            lock.unlock();
        }
    }
}

class Main {
    public static void main(String[] args) throws InterruptedException {
        PrinterQueue printerQueue = new PrinterQueue();
        Stream.of(1,2,3,4,5,6,7,8,9,10)
                .map( i -> new PrinterJob(printerQueue))
                .map(Thread::new)
                .forEach(Thread::start);

        Thread.sleep(10000);
    }
}
