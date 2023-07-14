package fly.web.event;

import fly.web.entity.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PostAddEvent {
    private Post post;
}
