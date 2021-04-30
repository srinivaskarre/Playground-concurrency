package producerconsumer;

import java.util.UUID;
import java.util.stream.Stream;

public class ThreadLocalEx {
    public static void main(String[] args) throws InterruptedException {
        Stream.of(1,2,3,4,5)
                .map(i -> new Job())
                .map(Thread::new)
                .forEach(Thread::start);

        Thread.sleep(10000);
    }
}


class Job implements Runnable{
    private static ThreadLocal<Context> threadLocal = new ThreadLocal<>();
    @Override
    public void run() {
        String name = UUID.randomUUID().toString();
        System.out.println(Thread.currentThread().getName()+ " username="+name);
        threadLocal.set(new Context(name));
        System.out.println(Thread.currentThread().getName()+ " tl="+threadLocal.get());
    }
}

class Context {
    public final String name;

    Context(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Context{" +
                "name='" + name + '\'' +
                '}';
    }
}
