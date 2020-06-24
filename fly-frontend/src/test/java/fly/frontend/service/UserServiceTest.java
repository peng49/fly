package fly.frontend.service;

import fly.frontend.FrontendSpringBootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;


import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.UUID;

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
}
