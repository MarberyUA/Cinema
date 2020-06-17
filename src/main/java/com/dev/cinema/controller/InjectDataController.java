package com.dev.cinema.controller;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.Role;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@RequestMapping("/inject")
@Controller
public class InjectDataController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostConstruct
    public void init() {
        Role.RoleName[] roles = Role.RoleName.values();
        for (int i = 0; i < roles.length; i++) {
            Role role = new Role();
            role.setRoleName(roles[i]);
            roleService.add(role);
        }
    }

    @GetMapping
    public void injectData() throws AuthenticationException {
        authenticationService.registration("admin", "admin@gmail.com", "1234");
    }

}
