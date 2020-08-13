package future;

import task.LongRunningCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureEx {
    public static void main(String[] args) {
        ExecutorService ecs = Executors.newFixedThreadPool(10);

        List<Future<String>> futures = new ArrayList<>();

        for(int i=1; i<=100;i++){
            futures.add(ecs.submit(new LongRunningCallable(1000)));
        }

        int counter = 0;

        while(counter<=100) {

            for (Future<String> f : futures) {
                if(f.isDone()){
                    try {
                        System.out.println(f.get());
                        counter++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        ecs.shutdown();

        System.out.println("main Thread completed");
    }
}
