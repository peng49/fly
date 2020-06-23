package fly.frontend;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("fly.frontend.mapper")
public class FrontendSpringBootApplication{
    public static void main(String[] args) {
        SpringApplication.run(FrontendSpringBootApplication.class, args);
    }

}
