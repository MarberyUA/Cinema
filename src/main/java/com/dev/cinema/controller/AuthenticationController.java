package com.dev.cinema.controller;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.request.AuthenticationRequestDto;
import com.dev.cinema.model.mapper.AuthenticationMapper;
import com.dev.cinema.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationMapper authenticationMapper;

    @PostMapping("/register")
    public void register(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto)
            throws AuthenticationException {
        User user = authenticationMapper.authenticationRequestDtoToUser(authenticationRequestDto);
        authenticationService.registration(user.getName(), user.getEmail(), user.getPassword());
    }
}
