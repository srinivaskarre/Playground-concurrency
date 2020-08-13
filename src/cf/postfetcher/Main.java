package cf.postfetcher;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().fetchPosts().join());
    }

    private CompletableFuture<List<Post>> fetchPosts() {
        List<CompletableFuture<List<Post>>> futures = Arrays.stream(SocialMedia.values())
                .map(this::fecthFromPlatform)
                .collect(toList());

        System.out.println(futures);

        return futures.stream()
                .reduce(combineApiCalls())
                .orElse(CompletableFuture.completedFuture(emptyList()));
    }

    private BinaryOperator<CompletableFuture<List<Post>>> combineApiCalls(){

        return (cf1,cf2) -> cf1.thenCombine(cf2, ((posts, posts2) ->
                Stream.concat(posts.stream(), posts2.stream()).collect(toList())));
    }

    private CompletableFuture<List<Post>> fecthFromPlatform(SocialMedia x) {
        return x.postFetcher.fetchPosts(getUser());
    }

    private User getUser() {
        return new User("mim");
    }
}
