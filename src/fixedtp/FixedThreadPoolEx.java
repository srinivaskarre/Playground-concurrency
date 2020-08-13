package fixedtp;

import task.LongRunningTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolEx {
    public static void main(String[] args) {
        ExecutorService ecs = Executors.newFixedThreadPool(10);
        for(int i=1; i<=100;i++){
            ecs.execute(new LongRunningTask(1000));
        }

        System.out.println(ecs.isShutdown());
        System.out.println(ecs.isTerminated());
        ecs.shutdown();

        //try submitting a task after shutdown trigger
        //ecs.execute(new LongRunningTask());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(ecs.isShutdown());
        System.out.println(ecs.isTerminated());

        System.out.println("Completed Main thread");
    }
}
