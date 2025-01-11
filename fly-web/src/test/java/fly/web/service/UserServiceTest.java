package fly.web.service;

import fly.web.FlyWebApplication;
import fly.web.entity.from.UserLoginFrom;
import fly.web.entity.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import jakarta.annotation.Resource;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyWebApplication.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Resource
    private OauthAccountService oauthAccountService;


    @Test
    public void lombokTest()
    {
        User admin = userService.getByUsername("peng49");

        Assert.isNull(admin,"vvv");
    }


    @Test
    public void findAllTest() {
        System.out.println(oauthAccountService.getPlatformAccount("gitee","123"));
    }

    @Test
    public void pathTest() throws FileNotFoundException {
        System.out.println(System.getProperty("user"));

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
