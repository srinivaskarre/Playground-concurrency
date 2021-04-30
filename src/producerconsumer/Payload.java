package producerconsumer;

import java.util.UUID;

public class Payload {
    private final UUID message;

    public Payload(UUID message) {
        this.message = message;
    }
}
