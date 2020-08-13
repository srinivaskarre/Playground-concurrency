package visibility;

public class VisibilityProblem {
    boolean flag = true;

    public void writeThread(){
        System.out.println("Before Updating");
        flag = false;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void readerThread(){
        while(flag){
            System.out.println("I;m running");
        }
    }
}

class ReaderTask implements  Runnable{

    private final VisibilityProblem visibilityProblem;

    public ReaderTask(VisibilityProblem visibilityProblem){
        this.visibilityProblem = visibilityProblem;
    }

    @Override
    public void run() {
        visibilityProblem.readerThread();
    }
}

class WriterTask implements  Runnable{

    private final VisibilityProblem visibilityProblem;

    public WriterTask(VisibilityProblem visibilityProblem){
        this.visibilityProblem = visibilityProblem;
    }

    @Override
    public void run() {
        visibilityProblem.writeThread();
    }
}

class Runner {
    public static void main(String[] args) throws InterruptedException {
        VisibilityProblem visibilityProblem = new VisibilityProblem();
        new Thread(new ReaderTask(visibilityProblem)).start();
        Thread.sleep(1000);
        new Thread(new WriterTask(visibilityProblem)).start();


        Thread.sleep(10000);

    }
}
