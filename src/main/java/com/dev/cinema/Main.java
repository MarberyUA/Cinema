package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        User user = new User();
        user.setName("Bob");
        user.setSurname("Marlie");
        user.setPassword("123");
        UserService userService = applicationContext.getBean(UserService.class);
        user = userService.create(user);
        System.out.println(userService.get(user.getId()));
    }
}
