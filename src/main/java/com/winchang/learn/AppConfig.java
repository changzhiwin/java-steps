package com.winchang.learn;

import java.time.ZoneId;
import org.springframework.context.ApplicationContext;
// import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Qualifier;


import com.winchang.learn.model.User;
import com.winchang.learn.service.UserService;

@Configuration
@ComponentScan
public class AppConfig 
{
    public static void main(String[] args) {
        System.out.println("Begin ...");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());

        System.out.println("-----------------");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println("beanName: " + beanName);
        }
    }

    @Bean("z")
    ZoneId createZoneOfZ() {
        return ZoneId.of("Z");
    }

    @Bean
    @Qualifier("utc8")
    ZoneId createZoneOfUTC8() {
        return ZoneId.of("UTC+08:00");
    }
}
