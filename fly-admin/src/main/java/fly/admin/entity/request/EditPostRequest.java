package fly.admin.entity.request;

import lombok.Data;

@Data
public class EditPostRequest {
    private int columnId;
    private int authorId;
    private String title;
    private String originalContent;
    private String content;
    private Integer status;
}
