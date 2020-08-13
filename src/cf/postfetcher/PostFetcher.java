package cf.postfetcher;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PostFetcher {
    CompletableFuture<List<Post>> fetchPosts(User user);
}

class Post {
    String post;
    String source;
    String userName;

    public Post(String post, String source,String userName) {
        this.post = post;
        this.source = source;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post='" + post + '\'' +
                ", source='" + source + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}

enum SocialMedia {
    FACEBOOK("FACEBOOK", new FBPostFecher()),
    TWITTER("TWITTER", new TwitterPostFetcher()),
    INSTAGRAM("INSTAGRAM", new IGPostFetcher());

    String type;
    PostFetcher postFetcher;

    SocialMedia(String type, PostFetcher postFetcher){
        this.postFetcher = postFetcher;
        this.type = type;
    }
}
