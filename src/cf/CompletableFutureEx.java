package cf;

import java.time.LocalTime;
import java.util.concurrent.*;

public class CompletableFutureEx {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new CompletableFutureEx().chainWithApply();
    }

    private void chainWithApply() throws InterruptedException, ExecutionException {
        System.out.println("Starting :: "+ LocalTime.now());
        Orderservice orderservice = new Orderservice();
        CompletableFuture.supplyAsync(orderservice::getOrder)
                .thenApply(orderservice::enrichOrder)
                .thenApply(orderservice::payment)
                .thenApply(orderservice::dispatch)
                .thenApplyAsync(orderservice::sendEmail);

        Thread.sleep(10000);

        System.out.println("Completed :: "+ LocalTime.now());
    }
}
