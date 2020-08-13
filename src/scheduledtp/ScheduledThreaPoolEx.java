package scheduledtp;

import task.LongRunningTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreaPoolEx {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(9);

        ses.schedule(new LongRunningTask(1000), 10, TimeUnit.SECONDS);

        ses.scheduleAtFixedRate(new LongRunningTask(1000), 15, 10, TimeUnit.SECONDS);

        ses.scheduleWithFixedDelay(new LongRunningTask(1000), 15, 10, TimeUnit.SECONDS);

        //ses.shutdown();

        while(!ses.isTerminated());

        System.out.println("main thread completed");
    }
}
