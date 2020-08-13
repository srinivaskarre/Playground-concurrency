package task;

public class LongRunningTask implements Runnable{

    private final int SLEEP_TIME;

    private final int DEFAULT_SLEEP_TIME = 10000;

    public LongRunningTask(){
        this.SLEEP_TIME = DEFAULT_SLEEP_TIME;
    }

    public LongRunningTask(int sleepTime){
        this.SLEEP_TIME = sleepTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed ::"+Thread.currentThread().getName());
    }
}
