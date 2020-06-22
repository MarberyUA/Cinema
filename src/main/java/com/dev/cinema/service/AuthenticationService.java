package com.dev.cinema.service;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;

public interface AuthenticationService {
    User registration(String name, String email, String password)
            throws AuthenticationException;
}
