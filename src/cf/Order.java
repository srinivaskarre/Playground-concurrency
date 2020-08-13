package cf;

import java.util.concurrent.TimeUnit;

public class Order {

    private String item = "abc";

    public String getItem(){
        return item;
    }
}

class Orderservice {
    public Order getOrder(){
        try{
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Wakeup");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Order();
    }

    public Order enrichOrder(Order order){
        try{
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Wakeup");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Order();
    }

    public Order payment(Order order){
        try{
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Wakeup");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Order();
    }

    public Order dispatch(Order order){
        try{
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Wakeup");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Order();
    }

    public Order sendEmail(Order order){
        try{
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Wakeup");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Order();
    }
}
