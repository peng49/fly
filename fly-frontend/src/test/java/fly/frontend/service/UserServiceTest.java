package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.entity.from.UserLoginFrom;
import fly.frontend.entity.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Resource
    private OauthAccountService oauthAccountService;

    @Test
    public void lombokTest()
    {
       User user = new User();
        user.setUsername(userService.getUniqueUsername("WE"));
        user.setAvatar("");
        userService.save(user);
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
