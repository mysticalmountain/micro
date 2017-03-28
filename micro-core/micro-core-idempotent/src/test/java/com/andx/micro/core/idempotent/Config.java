package com.andx.micro.core.idempotent;

import com.andx.micro.support.jpa.config.JpaConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by andongxu on 17-1-7.
 */
//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(JpaConfig.class)
public class Config {
}
