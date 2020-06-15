package com.dev.cinema.controller;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.request.OrderRequestDto;
import com.dev.cinema.model.dto.response.OrderResponseDto;
import com.dev.cinema.model.mapper.OrderMapper;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
<<<<<<< Updated upstream
    public void completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        User user = userService.get(orderMapper.orderRequestDtoToUserId(orderRequestDto));
=======
    public void completeOrder(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
>>>>>>> Stashed changes
        orderService.completeOrder(shoppingCartService.getByUser(user), user);
    }

    @GetMapping
<<<<<<< Updated upstream
    public List<OrderResponseDto> getUserOrders(@RequestParam Long userId) {
        User user = userService.get(userId);
=======
    public List<OrderResponseDto> getUserOrders(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
>>>>>>> Stashed changes
        return orderService.getOrderHistory(user)
                .stream()
                .map(order -> orderMapper.orderToOrderResponseDto(order))
                .collect(Collectors.toList());

    }
}
