package task;

import java.util.UUID;
import java.util.concurrent.Callable;

public class LongRunningCallable implements Callable<String> {

    private final int SLEEP_TIME;

    private final int DEFAULT_TIME = 5000;

    public LongRunningCallable(){
        this.SLEEP_TIME = DEFAULT_TIME;
    }

    public LongRunningCallable(int sleepTime){
        this.SLEEP_TIME = sleepTime;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(SLEEP_TIME);
        return UUID.randomUUID().toString();
    }
}
