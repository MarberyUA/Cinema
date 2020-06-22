package com.dev.cinema.controller;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/inject")
@Controller
public class InjectDataController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        User user = new User();
        user.setEmail("administrator@gmail.com");
        user.setName("admin");
        Set<Role> roles = Set.of(roleService.getRoleByName("ADMIN"),
                roleService.getRoleByName("USER"));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode("12345"));
        userService.create(user);
        shoppingCartService.registerNewShoppingCart(user);
    }

}
