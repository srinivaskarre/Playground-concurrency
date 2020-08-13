package locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionEx {

    private final ReentrantLock reentrantLock = new ReentrantLock();
    private final Condition condition = reentrantLock.newCondition();

    public void writeMethod(){
        reentrantLock.lock();

        System.out.println("Before awaiting");
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("After awaiting");

        reentrantLock.unlock();
    }

    public void readMethod(){
        reentrantLock.lock();

        System.out.println("Before notifying");
        //condition.signal();
        condition.signalAll();
        System.out.println("After notifying");

        reentrantLock.unlock();
    }
}

class Main{
    public static void main(String[] args) {
        //new Main().prepareLocks();
        new Main().signalAllEx();
    }

    private void signalAllEx() {
        ConditionEx conditionEx = new ConditionEx();

        new Thread(()-> conditionEx.writeMethod()).start();
        new Thread(()-> conditionEx.writeMethod()).start();
        new Thread(()-> conditionEx.readMethod()).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void prepareLocks() {
        ConditionEx conditionEx = new ConditionEx();
        new Thread(()-> conditionEx.readMethod()).start();

        new Thread(()-> conditionEx.writeMethod()).start();
        new Thread(()-> conditionEx.readMethod()).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
