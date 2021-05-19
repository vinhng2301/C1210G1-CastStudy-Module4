package com.example.demo.config;

import com.example.demo.formatter.AppUserFormatter;
import com.example.demo.formatter.ProductFormatter;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new AppUserFormatter(applicationContext.getBean(UserService.class)));
        registry.addFormatter(new ProductFormatter(applicationContext.getBean(ProductService.class)));
    }

}