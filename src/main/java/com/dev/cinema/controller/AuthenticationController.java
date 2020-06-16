package com.dev.cinema.controller;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.request.AuthenticationRequestDto;
import com.dev.cinema.model.mapper.AuthenticationMapper;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.RoleService;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationMapper authenticationMapper;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
        Role.RoleName[] roles = Role.RoleName.values();
        for (int i = 0; i < roles.length; i++) {
            Role role = new Role();
            role.setRoleName(roles[i]);
            roleService.add(role);
        }
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto)
            throws AuthenticationException {
        User user = authenticationMapper.authenticationRequestDtoToUser(authenticationRequestDto);
        authenticationService.registration(user.getName(), user.getEmail(),
                user.getPassword(), Set.of(roleService.getRoleByName("USER")));
    }
}
