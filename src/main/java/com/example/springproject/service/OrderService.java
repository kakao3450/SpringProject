package com.example.springproject.service;

import com.example.springproject.dto.OrderDto;
import com.example.springproject.dto.OrderResponseDto;
import com.example.springproject.entity.Product;
import com.example.springproject.entity.User;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

public interface OrderService {
    OrderResponseDto requestOrder(OrderDto orderDto);
    List<OrderResponseDto> listOrder();
    List<OrderResponseDto> listOrderByUserId(String userId);
    List<OrderResponseDto> listOrderByProductId(Long productId);
    OrderResponseDto listOrderById(Long Id);
}
