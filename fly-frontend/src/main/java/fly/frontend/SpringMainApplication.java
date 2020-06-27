package fly.frontend;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("fly.frontend.mapper")
public class SpringMainApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringMainApplication.class, args);
    }
}
