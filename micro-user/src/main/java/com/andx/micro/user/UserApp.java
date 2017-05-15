package com.andx.micro.user;

import com.andx.micro.core.idempotent.IdempotentConfig;
import com.andx.micro.core.validator.ValidatorConfig;
import com.andx.micro.support.jpa.config.JpaConfig;
import com.andx.micro.support.web.WebConfig;
import com.andx.micro.support.web.service.login.LoginService;
import com.andx.micro.user.service.handler.UsernamePasswordHandler;
import com.micro.support.session.config.SessionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by andongxu on 16-10-31.
 */
@Configuration
@EnableAutoConfiguration
//@EnableTransactionManagement
@ComponentScan(basePackages = {"com.andx.micro.user", "com.andx.micro.core.service"})
@Import({JpaConfig.class, ValidatorConfig.class, IdempotentConfig.class, WebConfig.class, SessionConfig.class})
public class UserApp {

//    public NewUsers(EditUserHandler editUserHandler, EditUserProfileHandler editUserProfileHandler) {
//        serviceHandlers.add(editUserHandler);
//        serviceHandlers.add(editUserProfileHandler);
//    }

//    @Bean
//    public NewUsers newUsers(EditUserHandler editUserHandler, EditUserProfileHandler editUserProfileHandler)  {
//        return new NewUsers(editUserHandler, editUserProfileHandler);
//    }

    @Bean
    public LoginService loginService(UsernamePasswordHandler usernamePasswordHandler) {
        return new LoginService(usernamePasswordHandler);
    }

    public static void main(String [] args) {
        SpringApplication.run(UserApp.class, args);
    }
}
