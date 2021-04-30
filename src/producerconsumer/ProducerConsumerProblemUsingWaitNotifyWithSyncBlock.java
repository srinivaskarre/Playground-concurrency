package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class ProducerConsumerProblemUsingWaitNotifyWithSyncBlock<T> {
    private final Queue<T> queue;
    private final int size;
    private final Object notFullCondition = new Object();

    public ProducerConsumerProblemUsingWaitNotifyWithSyncBlock(Queue<T> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    public void add(T item){
        synchronized (notFullCondition) {
            try {
                while (queue.size() == size) {
                    System.out.println("Waiting queue is full");
                    notFullCondition.wait();
                }
                queue.add(item);
                notFullCondition.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public T get(){
        synchronized (notFullCondition) {
            try {
                while (queue.isEmpty()) {
                    System.out.println("waiting queue is empty");
                    notFullCondition.wait();
                }
                T item = queue.remove();
                notFullCondition.notifyAll();
                return item;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        ProducerConsumerProblemUsingWaitNotifyWithSyncBlock<Payload> p = new ProducerConsumerProblemUsingWaitNotifyWithSyncBlock<>(new LinkedList(), 10);
        final Runnable consumer = () -> {
            while(true){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(p.get());
            }
        };

        final Runnable producer = () -> {
            while(true){
                p.add(new Payload(UUID.randomUUID()));
            }
        };

        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
