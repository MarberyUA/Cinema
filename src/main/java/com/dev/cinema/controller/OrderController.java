package com.dev.cinema.controller;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.response.OrderResponseDto;
import com.dev.cinema.model.mapper.OrderMapper;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        orderService.completeOrder(shoppingCartService.getByUser(user), user);
    }

    @GetMapping
    public List<OrderResponseDto> getUserOrders(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return orderService.getOrderHistory(user)
                .stream()
                .map(order -> orderMapper.orderToOrderResponseDto(order))
                .collect(Collectors.toList());

    }
}
