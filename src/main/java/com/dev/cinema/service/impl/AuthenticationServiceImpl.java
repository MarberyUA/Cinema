package com.dev.cinema.service.impl;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        try {
            User user = userService.findByEmail(email);
            if (user == null) {
                throw new AuthenticationException("The email is incorrect");
            }
            String hashedPassword = HashUtil.hashPassword(password, user.getSalt());
            if (user.getPassword().equals(hashedPassword)) {
                return user;
            }
            throw new AuthenticationException("The password is incorrect");
        } catch (DataProcessingException e) {
            throw new AuthenticationException("The email is incorrect");
        }
    }

    @Override
    public User registration(String email, String password) throws AuthenticationException {
        try {
            User user = new User();
            user.setEmail(email);
            user.setSalt(HashUtil.getSalt());
            user.setPassword(HashUtil.hashPassword(password, user.getSalt()));
            userService.create(user);
            return user;
        } catch (DataProcessingException e) {
            throw new AuthenticationException("The user with this email "
                    + "had already registered!", e);
        }
    }
}
