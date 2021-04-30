package fjp;

import java.util.concurrent.*;

public class Fibonacci extends RecursiveTask<Integer> {

    private final int n;

    public Fibonacci(int n){
        this.n = n;
    }
    @Override
    protected Integer compute() {
        //System.out.println("Thread ::"+Thread.currentThread().getName());
        if(n<=1){
            return 1;
        }

        Fibonacci f1 = new Fibonacci(n-1);
        f1.fork();

        Fibonacci f2 = new Fibonacci(n-2);
        f2.fork();

        return f1.join() + f2.join();
    }


    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        ForkJoinTask<Integer> submit = fjp.submit(new Fibonacci(20));
        do
        {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", fjp.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", fjp.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", fjp.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", fjp.getStealCount());
            System.out.printf("******************************************\n");
            try
            {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        } while ((!submit.isDone()));
        fjp.shutdown();
        System.out.println(submit.join());
    }
}
