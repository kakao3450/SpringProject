package com.example.springproject.dao.impl;

import com.example.springproject.dao.OrderDao;
import com.example.springproject.entity.Order;
import com.example.springproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderDaoImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @Override
    public Order insertOrder(Order order) {
        Order saveOrder = orderRepository.save(order);
        return saveOrder;
    }

    @Override
    public List<Order> listOrderByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> listProductByProductId(Long productId) {
        return orderRepository.findByProductId(productId);
    }

    @Override
    public List<Order> listOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order listOrderById(Long id) {

        return orderRepository.getReferenceById(id);
    }
}
