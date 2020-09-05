package fly.frontend.pojo;

import lombok.Data;

@Data
public class PostFilterCondition {
    private String list = "all";

    private int authorId;

    private int status;

    private int columnId;

    private String orderBy;
}
