package fly.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@SpringBootApplication
@MapperScan("fly.web.dao")
public class FlyWebApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(FlyWebApplication.class, args);
    }

    /**
     * 统一设置时区
     */
    @PostConstruct
    public void started()
    {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Chongqing"));
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DateTimeFormatter getSimpleDateFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
