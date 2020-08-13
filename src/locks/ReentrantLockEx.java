package locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx {

    private ReentrantLock reentrantLock =  new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ecs = Executors.newFixedThreadPool(10);
        ReentrantLockEx reentrantLockEx = new ReentrantLockEx();

        for(int i=0;i<100;i++){
            ecs.submit(()-> reentrantLockEx.runReentrantLock());
        }

        Thread.sleep(10000);

    }

    private void runReentrantLock(){
        reentrantLock.lock();
        try{
            System.out.println("In Lock Context..exiting :: "+Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println("out of sleep "+Thread.currentThread().getName());
            condition.await();
            System.out.println("After wait "+Thread.currentThread().getName());
        }catch (Exception e){

        }finally {
            reentrantLock.unlock();
        }
    }
}
