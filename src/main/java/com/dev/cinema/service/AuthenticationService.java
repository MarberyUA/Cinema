package com.dev.cinema.service;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import java.util.Set;

public interface AuthenticationService {
    User registration(String name, String email, String password, Set<Role> roles)
            throws AuthenticationException;
}
