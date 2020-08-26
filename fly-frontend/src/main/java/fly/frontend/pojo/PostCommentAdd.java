package fly.frontend.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class PostCommentAdd {

    @Min(value = 1,message = "文章Id不能为空")
    private int postId;

    private int parentId;

    @NotBlank(message = "评论内容不能为空")
    private String content;
}
