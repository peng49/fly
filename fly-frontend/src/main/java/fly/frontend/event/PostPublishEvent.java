package fly.frontend.event;

import fly.frontend.entity.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PostPublishEvent {
    private Post post;
}
