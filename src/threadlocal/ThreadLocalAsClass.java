package threadlocal;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalAsClass {
    public static void main(String[] args) {
        new ThreadLocalAsClass().main();
    }

    private void main() {
        ExecutorService ecs = Executors.newFixedThreadPool(10);
        for(int i=0;i<100;i++){
            ecs.execute(new Task());
        }

        ecs.shutdown();
        System.out.println("main completed");
    }


}

class UserThreadLocal{

    public static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(()->
            UUID.randomUUID().toString());
}

class Task implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"::"+UserThreadLocal.threadLocal.get());
    }
}
