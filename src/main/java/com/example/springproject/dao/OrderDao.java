package com.example.springproject.dao;

import com.example.springproject.entity.Order;

import java.util.List;

public interface OrderDao {
    Order insertOrder(Order order);
    List<Order> listOrderByUserId(String userId);
    List<Order> listProductByProductId(Long productId);
    List<Order> listOrder();
    Order listOrderById(Long id);

}
