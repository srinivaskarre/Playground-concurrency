package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerProblemUsingLockCondition<T> {

    private final Queue<T> queue;
    private final int size;
    private final ReentrantLock reentrantLock = new ReentrantLock(true);
    private final Condition notFullCondition = reentrantLock.newCondition();
    private final Condition notEmptyCondition = reentrantLock.newCondition();

    public ProducerConsumerProblemUsingLockCondition(Queue<T> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    public void add(T item){
        reentrantLock.lock();
        System.out.println("add locked");
        try {
            while (queue.size() == size) {
                notFullCondition.await();
            }
            queue.add(item);
            notEmptyCondition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("add unlocked");
            reentrantLock.unlock();
        }
    }

    public T get(){
        reentrantLock.lock();
        System.out.println("get locked");
        try{
            while(queue.size() == 0){
                notEmptyCondition.await();
            }
            T item = queue.remove();
            notFullCondition.signalAll();
            return item;
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("get unlocked");
            reentrantLock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        ProducerConsumerProblemUsingLockCondition<Payload> p = new ProducerConsumerProblemUsingLockCondition<>(new LinkedList(), 10);
        final Runnable consumer = () -> {
            while(true){
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
