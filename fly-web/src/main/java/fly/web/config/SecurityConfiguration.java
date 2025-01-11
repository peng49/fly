package fly.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fly.web.service.UserService;
import fly.web.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/static/**", "/column/**", "/user/login", "/post/detail/*", "/u/*"
                                , "/oauth/**", "/error/**", "/user/register", "/user/forget", "/favicon.ico",
                                "/user-tag/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> {
                    csrf.ignoringRequestMatchers("/**");
                })
                .formLogin(login -> {
                    login.loginPage("/user/login")
                            .failureHandler((request, response, exception) -> {
                                response.setContentType("application/json; charset=utf-8");
                                response.getWriter().write(new ObjectMapper().writeValueAsString(HttpUtils.fail(exception.getMessage())));
                            })
                            .successHandler((request, response, exception) -> {
                                response.setContentType("application/json; charset=utf-8");
                                response.getWriter().write(new ObjectMapper().writeValueAsString(HttpUtils.success()));
                            });
                })
                .logout(logout -> {
                    logout.logoutUrl("/user/logout");
                })
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint((request, response, authException) -> {
                        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
                            // 返回json
                            response.setContentType("application/json; charset=utf-8");
                            response.getWriter().write(new ObjectMapper().writeValueAsString(HttpUtils.fail(authException.getMessage())));
                        } else {
                            response.sendRedirect("/user/login");
                        }
                    });
                });
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            fly.web.entity.model.User user = userService.getByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(username);
            }
            return User.withUsername(username).password(user.getPassword()).build();
        };
    }
}
