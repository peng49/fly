package fly.frontend.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

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
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int isAdmin;
}
