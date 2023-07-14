package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@TableName(value = "friend_links")
public class FriendLink {
    private Long id;
    private String name;
    private String url;
    private int status;
}
