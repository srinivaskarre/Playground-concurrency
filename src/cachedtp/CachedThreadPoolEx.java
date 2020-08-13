package cachedtp;

import task.LongRunningTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * cached threadpool is elastic in nature, could grow and shrink on task submission rate basis
 */
public class CachedThreadPoolEx {

    public static void main(String[] args) {

        ExecutorService ecs = Executors.newCachedThreadPool();

        for(int i=0;i<100;i++){
            ecs.execute(new LongRunningTask(1000));
        }

        ecs.shutdown();

        while(!ecs.isTerminated());

        System.out.println("Main thread completed ::");
    }
}
