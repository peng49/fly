package fly.web;


import jakarta.annotation.PostConstruct;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


@SpringBootApplication
@MapperScan("fly.web.dao")
public class FlyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlyWebApplication.class, args);
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

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
