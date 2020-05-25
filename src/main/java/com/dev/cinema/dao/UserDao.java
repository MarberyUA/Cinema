package com.dev.cinema.dao;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;

public interface UserDao {
    User create(User user);

    User get(Long userId);

    User findByEmail(String userEmail) throws AuthenticationException;
}
