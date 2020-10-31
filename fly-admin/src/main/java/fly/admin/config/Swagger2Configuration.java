package fly.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket createAuthApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("权限管理")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("fly.admin"))
                .paths(PathSelectors.ant("/api/auth/**"))
                .build()
                .enable(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("权限管理")
                .description("description content")
                .version("v1.0")
                .build();
    }

    @Bean
    public Docket createThemeApi() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(
                new ParameterBuilder()
                        .name("X-Token")
                        .parameterType("Header")
                        .required(true)
                        .description("Token")
                        .modelRef(new ModelRef("string"))
                        .build()
        );


        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("主题管理")
                .apiInfo(new ApiInfoBuilder().title("主题管理").build())
                .globalOperationParameters(parameters) //全局参数设置
                .select()
                .apis(RequestHandlerSelectors.basePackage("fly.admin"))
                .paths(PathSelectors.ant("/api/**"))
                .paths(PathSelectors.regex("^(?!/api/auth).*"))
                .build()
                .enable(true);
    }
}
