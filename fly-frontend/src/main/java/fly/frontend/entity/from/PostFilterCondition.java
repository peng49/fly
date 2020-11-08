package fly.frontend.entity.from;

import fly.frontend.entity.model.Post;
import lombok.Data;

import java.util.function.Function;

@Data
public class PostFilterCondition {
    private String list = "all";

    private int authorId;

    private int status;

    private int columnId;

    private String orderBy;
}
