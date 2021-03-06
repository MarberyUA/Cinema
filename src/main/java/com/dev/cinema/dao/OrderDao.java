package com.dev.cinema.dao;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;

public interface OrderDao {
    Order addOrder(Order order);

    List<Order> getOrderHistory(User user);
}
