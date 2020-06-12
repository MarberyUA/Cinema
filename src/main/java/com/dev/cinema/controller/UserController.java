package com.dev.cinema.controller;

import com.dev.cinema.model.dto.response.UserResponseDto;
import com.dev.cinema.model.mapper.UserMapper;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/byemail")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return userMapper.userToUserResponseDto(userService.findByEmail(email));
    }
}
