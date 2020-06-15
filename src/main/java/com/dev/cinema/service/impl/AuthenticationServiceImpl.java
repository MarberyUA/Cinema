package com.dev.cinema.service.impl;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public User registration(String name, String email, String password, Set<Role> roles)
            throws AuthenticationException {
        try {
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setRoles(roles);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            userService.create(user);
            shoppingCartService.registerNewShoppingCart(user);
            return user;
        } catch (DataProcessingException e) {
            throw new AuthenticationException("The user with this email "
                    + "had already registered!", e);
        }
    }
}
