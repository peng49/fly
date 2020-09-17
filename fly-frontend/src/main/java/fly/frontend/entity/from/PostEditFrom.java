package fly.frontend.entity.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostEditFrom {
    private String action;

    private int columnId;

    private int userId;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

    private String originalContent;
}
