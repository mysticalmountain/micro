package com.andx.micro.user;

import com.andx.micro.core.idempotent.IdempotentConfig;
import com.andx.micro.core.validator.ValidatorConfig;
import com.andx.micro.support.jpa.config.JpaConfig;
import com.andx.micro.support.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by andongxu on 16-10-31.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.andx.micro.user", "com.andx.micro.core.service"})
@Import({JpaConfig.class, ValidatorConfig.class, IdempotentConfig.class, WebConfig.class})
public class UserApp {
    public static void main(String [] args) {
        SpringApplication.run(UserApp.class, args);
    }
}
