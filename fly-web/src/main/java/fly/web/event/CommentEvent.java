package fly.web.event;


import fly.web.entity.model.PostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CommentEvent {
    private final PostComment postComment;
}
