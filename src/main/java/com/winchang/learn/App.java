package com.winchang.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.winchang.learn.model.User;
import com.winchang.learn.service.UserService;

public class App 
{
    public static void main(String[] args) {
        System.out.println("start ...");
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("bob@example.com", "password");
        System.out.println(user.getName());
    }
}
