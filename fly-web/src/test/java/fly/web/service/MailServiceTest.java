package fly.web.service;

import fly.web.FlyWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FlyWebApplication.class})
public class MailServiceTest {

    @Autowired
    private JavaMailSender mailSender;


    @Test
    public void sendTest() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yypeng@sfcservice.com");
        message.setTo("1219955253@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }
}
