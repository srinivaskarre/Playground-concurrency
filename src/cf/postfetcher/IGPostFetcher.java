package cf.postfetcher;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class IGPostFetcher implements PostFetcher{
    final String FB = "IG";

    @Override
    public CompletableFuture<List<Post>> fetchPosts(User user) {
        Executor executor = CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS);
        return CompletableFuture.supplyAsync(()-> fecthPosts(user), executor);
    }

    private List<Post> fecthPosts(User user) {
        return Stream.iterate(1, x-> x<5, x-> x+1)
                .map(x-> new Post(UUID.randomUUID().toString(), FB,  user.name))
                .collect(toList());
    }
}
