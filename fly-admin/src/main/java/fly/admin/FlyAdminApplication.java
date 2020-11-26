package fly.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class FlyAdminApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(FlyAdminApplication.class, args);
    }

    @Bean
    public DateTimeFormatter getSimpleDateFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
}
