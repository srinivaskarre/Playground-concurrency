package producerconsumer;

/**
 * To Solve the dead lock, we need to reorder the lock aquisition in the same order
 *
 * like all threads should acquire lock b, then lock a then lock c
 *
 * so all threads should be trying to acquire the same/different condition
 */
public class CreateDeadLock {
    public static void main(String[] args) throws InterruptedException {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        final Runnable firstLockAThenB = () -> {
            synchronized (resourceA){
                System.out.println("firstLockAThenB locked a");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
                synchronized (resourceB){
                    System.out.println("firstLockAThenB locked b");
                }
            }
        };

        final Runnable firstLockBThenA = () -> {
            synchronized (resourceB){
                System.out.println("firstLockAThenB locked b");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
                synchronized (resourceA){
                    System.out.println("firstLockBThenA locked a");
                }
            }
        };

        new Thread(firstLockAThenB).start();
        new Thread(firstLockBThenA).start();

        Thread.sleep(100000);
    }
}

class ResourceA {
    private int i;
}

class ResourceB {
    private int j;
}
