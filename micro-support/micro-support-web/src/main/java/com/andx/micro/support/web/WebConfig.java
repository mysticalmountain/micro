package com.andx.micro.support.web;

import com.andx.micro.support.web.filter.SessionFilter;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.validation.Cas10TicketValidationFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.session.MapSessionRepository;
//import org.springframework.session.SessionRepository;
//import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

/**
 * Created by andongxu on 9/11/16.
 */
//@EnableWebMvc
@ComponentScan
@EnableAutoConfiguration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }


//    @Bean
//    public AuthenticationFilter authenticationFilter() {
//        return new AuthenticationFilter();
//    }

//    @Bean
//    public Cas10TicketValidationFilter cas10TicketValidationFilter() {
//        return new Cas10TicketValidationFilter();
//    }

    @Bean
    public FilterRegistrationBean authenticationFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthenticationFilter());
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("casServerLoginUrl", "http://server.cas.com:7081/cas/login");
        filterRegistrationBean.addInitParameter("serverName", "http://client.cas.com");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean cas10TicketValidationFilter () {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new Cas10TicketValidationFilter());
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("casServerUrlPrefix", "http://server.cas.com:7081/cas");
        filterRegistrationBean.addInitParameter("serverName", "http://client.cas.com");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
