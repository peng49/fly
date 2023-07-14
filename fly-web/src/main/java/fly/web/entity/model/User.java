package fly.web.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("users")
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String avatar;
    private String city;
    private int experience;
    private String signature;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int isAdmin;
}
