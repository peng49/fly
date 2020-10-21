package fly.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class FlyAdminApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(FlyAdminApplication.class, args);
    }

    @Bean
    public SimpleDateFormat getSimpleDateFormat()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
