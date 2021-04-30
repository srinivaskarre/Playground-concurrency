package producerconsumer;
/**
This class depicts that synchroized metohd locks entire object
 so if multiple synchronized methods called on same instance then one after other is called because of same object synchronization
 */
public class SynchronizedMethodsSequenceCheck {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedCheck sync = new SynchronizedCheck();
        final Runnable getTask = () -> {
          sync.get();
        };

        final Runnable putTask = () -> {
            sync.put();
        };

        final Runnable delete = () -> {
            sync.delete();
        };

        new Thread(getTask).start();
        new Thread(putTask).start();
        new Thread(delete).start();

        Thread.sleep(100000000);
    }
}


class SynchronizedCheck {
    public synchronized void get(){
        System.out.println("Enter Get");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            System.out.println();
        }
        System.out.println("Exit Get");
    }

    public synchronized void put(){
        System.out.println("Enter put");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            System.out.println();
        }
        System.out.println("Exit put");
    }

    public synchronized void delete(){
        System.out.println("Enter delete");
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            System.out.println();
        }
        System.out.println("Exit delete");
    }
}
