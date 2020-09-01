package fly.frontend.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePassword {
    @NotBlank(message = "密码不能为空")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}
