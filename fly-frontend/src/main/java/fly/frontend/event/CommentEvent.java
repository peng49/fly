package fly.frontend.event;


import fly.frontend.entity.model.PostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CommentEvent {
    private PostComment postComment;
}
