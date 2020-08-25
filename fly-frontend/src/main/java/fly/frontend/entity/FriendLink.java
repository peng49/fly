package fly.frontend.entity;

import lombok.Data;

@Data
public class FriendLink {
    private int id;
    private String name;
    private String url;
    private int status;
}
