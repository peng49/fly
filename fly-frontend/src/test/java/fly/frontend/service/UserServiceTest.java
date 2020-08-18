package fly.frontend.service;

import fly.frontend.FlyFrontendApplication;
import fly.frontend.pojo.UserLogin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


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


    @Test
    public void findAllTest() {
//        System.out.println(userService.findAll());

        String encode = new BCryptPasswordEncoder().encode("123");
        System.out.println(encode);

        System.out.println(new BCryptPasswordEncoder().matches("1234", encode));
    }

    @Test
    public void getByIdTest() {
        System.out.println(userService.getById(1));
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

        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("");

        Set<ConstraintViolation<UserLogin>> validate = validator.validate(userLogin);

        for (ConstraintViolation<UserLogin> userLoginConstraintViolation : validate) {
            System.out.println(userLoginConstraintViolation.getRootBean().getClass().getName());
            System.out.println(userLoginConstraintViolation.getPropertyPath());
            System.out.println(userLoginConstraintViolation.getMessage());
        }
    }
}
