package fly.web.entity.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostEditFrom {
    private String action;

    private int columnId;

    private Long postId;

    private Long userId;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

    @NotBlank(message = "原内容不能为空")
    private String originalContent;
}
