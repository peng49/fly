package fly.admin.entity.request;

import lombok.Data;

@Data
public class EditFriendLinkRequest {
    private String name;
    private String url;
    private int status;
}
