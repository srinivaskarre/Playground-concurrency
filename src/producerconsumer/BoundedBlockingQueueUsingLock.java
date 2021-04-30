package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBlockingQueueUsingLock {
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<Integer> queue = new LinkedList<>();

    public BoundedBlockingQueueUsingLock(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try{
            while(queue.size() == capacity){
                condition.await();
            }
            queue.add(element);
            condition.signalAll();
        }catch(Exception e){

        }finally{
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try{
            while(queue.size() == 0){
                condition.await();
            }

            int element = queue.remove();
            condition.signalAll();
            return element;
        }catch(Exception e){

        }finally{
            lock.unlock();
        }

        return -1;
    }

    public int size() {
        return queue.size();
    }
}
