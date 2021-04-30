package producerconsumer;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerUsingBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Payload> queue = new ArrayBlockingQueue<>(10);
        final Runnable consumer = () -> {
            while (true){
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        final Runnable producer = () -> {
          while(true){
              try {
                  queue.put(new Payload(UUID.randomUUID()));
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        };

        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(producer).start();
        new Thread(producer).start();

        Thread.sleep(1000000L);
    }
}


