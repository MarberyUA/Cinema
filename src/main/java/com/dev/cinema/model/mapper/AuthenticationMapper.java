package com.dev.cinema.model.mapper;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.request.AuthenticationRequestDto;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {
    public User authenticationRequestDtoToUser(AuthenticationRequestDto authenticationRequestDto) {
        User user = new User();
        user.setEmail(authenticationRequestDto.getEmail());
        user.setName(authenticationRequestDto.getName());
        user.setPassword(authenticationRequestDto.getPassword());
        return user;
    }
}
