package fly.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@SpringBootApplication
public class FlyAdminApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(FlyAdminApplication.class, args);
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
    public DateTimeFormatter getSimpleDateFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
}
