package fly.frontend.service;

import fly.frontend.FrontendSpringBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FrontendSpringBootApplication.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void findAllTest() {
//        System.out.println(userService.findAll());

        String encode = new BCryptPasswordEncoder().encode("123");
        System.out.println(encode);

        System.out.println(new BCryptPasswordEncoder().matches("1234",encode));

    }

}
