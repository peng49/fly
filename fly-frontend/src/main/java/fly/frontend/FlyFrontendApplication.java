package fly.frontend;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
@MapperScan("fly.frontend.dao")
public class FlyFrontendApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(FlyFrontendApplication.class, args);
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
