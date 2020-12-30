package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.config.ShiroConfiguration;
import fly.frontend.entity.from.UserLoginFrom;
import fly.frontend.entity.model.User;
import fly.frontend.shiro.OauthToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyFrontendApplication.class})
@Import(ShiroConfiguration.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Resource
    private OauthAccountService oauthAccountService;

    @Resource
    private DefaultWebSecurityManager defaultWebSecurityManager;

    @Test
    public void lombokTest()
    {
        User admin = userService.getByUsername("peng49");
        OauthToken oauthToken = new OauthToken(admin);

        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        Subject subject = SecurityUtils.getSubject();


        subject.login(oauthToken);
    }


    @Test
    public void findAllTest() {
        System.out.println(oauthAccountService.getPlatformAccount("gitee","123"));
    }

    @Test
    public void pathTest() throws FileNotFoundException {
        System.out.println(System.getProperty("user.dir"));

        System.out.println(UUID.randomUUID());

        System.out.println("123.jpg".substring("123.jpg".indexOf('.')));
//        System.out.println(ResourceUtils.getURL("classpath:").getPath());
    }

    @Test
    public void validateTest()
    {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        UserLoginFrom userLoginFrom = new UserLoginFrom();
        userLoginFrom.setUsername("");

        Set<ConstraintViolation<UserLoginFrom>> validate = validator.validate(userLoginFrom);

        for (ConstraintViolation<UserLoginFrom> userLoginConstraintViolation : validate) {
            System.out.println(userLoginConstraintViolation.getRootBean().getClass().getName());
            System.out.println(userLoginConstraintViolation.getPropertyPath());
            System.out.println(userLoginConstraintViolation.getMessage());
        }
    }
}
