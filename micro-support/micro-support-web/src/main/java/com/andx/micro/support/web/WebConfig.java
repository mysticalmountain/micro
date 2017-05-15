package com.andx.micro.support.web;

import com.andx.micro.support.web.controller.ErrorController;
import com.andx.micro.support.web.controller.MicroErrorController;
import com.andx.micro.support.web.filter.SessionFilter;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.validation.Cas10TicketValidationFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
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

//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/views/");
//        viewResolver.setSuffix(".html");
//        return viewResolver;
//    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
//    }

//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        return new MappingJackson2HttpMessageConverter();
//    }

//    @Bean
//    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
//        return new MappingJackson2XmlHttpMessageConverter();
//    }

//    @Bean
//    public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
//        return  new Jaxb2RootElementHttpMessageConverter();
//    }

//    @Bean
//    public AuthenticationFilter authenticationFilter() {
//        return new AuthenticationFilter();
//    }

//    @Bean
//    public Cas10TicketValidationFilter cas10TicketValidationFilter() {
//        return new Cas10TicketValidationFilter();
//    }

//    @Bean
//    public FilterRegistrationBean authenticationFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new AuthenticationFilter());
//        filterRegistrationBean.setEnabled(true);
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("casServerLoginUrl", "http://server.cas.com:7081/cas/login");
//        filterRegistrationBean.addInitParameter("serverName", "http://client.cas.com");
//        filterRegistrationBean.setOrder(1);
//        return filterRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean cas10TicketValidationFilter () {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new Cas10TicketValidationFilter());
//        filterRegistrationBean.setEnabled(true);
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("casServerUrlPrefix", "http://server.cas.com:7081/cas");
//        filterRegistrationBean.addInitParameter("serverName", "http://client.cas.com");
//        filterRegistrationBean.setOrder(1);
//        return filterRegistrationBean;
//    }

    @Bean
    public MicroErrorController microErrorController(ErrorAttributes errorAttributes) {
        return new MicroErrorController(errorAttributes);
    }
}
