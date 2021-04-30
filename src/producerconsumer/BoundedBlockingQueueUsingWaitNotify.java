package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBlockingQueue {
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private Queue<Integer> queue = new LinkedList<>();

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try{
            while(queue.size() == capacity){
                lock.wait();
            }
            queue.add(element);
            lock.notifyAll();
        }catch(Exception e){

        }finally{
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try{
            while(queue.size() == 0){
                lock.wait();
            }

            int element = queue.remove();
            lock.notifyAll();
            return element;
        }catch(Exception e){

        }finally{
            lock.unlock();
        }
        return 0;
    }

    public int size() {
        return queue.size();
    }
}
