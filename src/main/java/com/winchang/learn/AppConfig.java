package com.winchang.learn;

import java.time.ZoneId;
import org.springframework.context.ApplicationContext;
// import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.stream.LongStream;


import com.winchang.learn.model.User;
import com.winchang.learn.service.UserService;
import com.winchang.learn.service.NumberService;

@Configuration
@ComponentScan
public class AppConfig 
{
    public static void main(String[] args) {
        System.out.println("Start...");
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

        NumberService ns = context.getBean(NumberService.class);
        ns.getLongStream().limit(10).forEach(System.out::println);

        NumberService ns1 = context.getBean(NumberService.class);
        ns1.getLongStream().limit(10).forEach(System.out::println);
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
