package fly.admin.entity.request;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class EditPostRequest {
    @NotNull
    @Min(1)
    private int columnId;

    @NotNull
    @Min(1)
    private Long authorId;

    @NotNull
    private String title;
    private String originalContent;
    private String content;
    private Integer status;
}
