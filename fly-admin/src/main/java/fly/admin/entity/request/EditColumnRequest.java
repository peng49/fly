package fly.admin.entity.request;

import lombok.Data;

@Data
public class EditColumnRequest {
    private String name;
    private int sort;
}
