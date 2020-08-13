package singletp;

import task.LongRunningTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPoolEx {
    public static void main(String[] args) {
        ExecutorService ecs = Executors.newSingleThreadExecutor();

        for(int i=0;i<100;i++){
            ecs.execute(new LongRunningTask(1));
        }

        ecs.shutdown();

        while(!ecs.isTerminated());

        System.out.println("main thread completed");
    }
}
