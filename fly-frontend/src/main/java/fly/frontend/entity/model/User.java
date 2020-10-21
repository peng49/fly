package fly.frontend.entity.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private int id;
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
