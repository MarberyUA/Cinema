package com.dev.cinema.model.mapper;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto userToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        userResponseDto.setSurname(user.getSurname());
        return userResponseDto;
    }
}
