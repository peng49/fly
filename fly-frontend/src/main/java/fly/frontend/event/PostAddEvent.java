package fly.frontend.event;

import fly.frontend.entity.po.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PostAddEvent {
    private Post post;
}
