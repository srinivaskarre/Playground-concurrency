package visibility;

public class VisibilityProblemWithBoolean {

    boolean flag = true;
    public static void main(String[] args) {
        new VisibilityProblemWithBoolean().main();
    }

    private void main() {
        new Thread(new Task()).start();
        new Thread(new UpdateTask()).start();
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    class Task implements Runnable{

        @Override
        public void run() {
            while (flag){
                System.out.println("Rinning");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class UpdateTask implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                flag = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Done updating");
        }
    }



}
