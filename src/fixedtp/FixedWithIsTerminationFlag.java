package fixedtp;

import task.LongRunningTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedWithIsTerminationFlag {

    public static void main(String[] args) {
        ExecutorService ecs = Executors.newFixedThreadPool(10);

        for(int i=0;i<100;i++){
            ecs.execute(new LongRunningTask(1000));
        }

        ecs.shutdown();

        System.out.println(ecs.isTerminated());

        while(!ecs.isTerminated());

        System.out.println("main thread completed");
    }
}
