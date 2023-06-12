package com.example.springproject.service.imple;

import com.example.springproject.dao.OrderDao;
import com.example.springproject.dao.ProductDao;
import com.example.springproject.dto.OrderDto;
import com.example.springproject.dto.OrderResponseDto;
import com.example.springproject.entity.Order;
import com.example.springproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ProductDao productDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
    }


    @Override
    public OrderResponseDto requestOrder(OrderDto orderDto) {
        Order order = new Order(orderDto.getProductId(), orderDto.getPrice()
                , orderDto.getProductName(), orderDto.getUserId(), orderDto.getUserName(),
        LocalDateTime.now(), LocalDateTime.now());  // 왜 order에 메서드를 추가해야하는가..
        Order addOrder = orderDao.insertOrder(order);

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(addOrder.getId());
        orderResponseDto.setProductId(addOrder.getProductId());
        orderResponseDto.setProductName(addOrder.getProductName());
        orderResponseDto.setPrice(addOrder.getPrice());
        orderResponseDto.setUserId(addOrder.getUserId());
        orderResponseDto.setUserName(addOrder.getUserName());

        return orderResponseDto;
    }

    @Override
    public List<OrderResponseDto> listOrder() {
        List<Order> orders = orderDao.listOrder();
        List<OrderResponseDto> orderResponseDtos = orders.stream().map(item ->
                new OrderResponseDto(item)).collect(Collectors.toList());
        return orderResponseDtos;
    }

    @Override
    public List<OrderResponseDto> listOrderByUserId(String userId) {
        List<Order> orders = orderDao.listOrderByUserId(userId);
        List<OrderResponseDto> orderResponseDtos = orders.stream().map(item ->
                new OrderResponseDto(item)).collect(Collectors.toList());
        return orderResponseDtos;
    }

    @Override
    public List<OrderResponseDto> listOrderByProductId(Long productId) {
        List<Order> orders = orderDao.listProductByProductId(productId);
        List<OrderResponseDto> orderResponseDtos = orders.stream().map(item ->
                new OrderResponseDto(item)).collect(Collectors.toList());
        return orderResponseDtos;
    }

    @Override
    public OrderResponseDto listOrderById(Long Id) {
        Order order = orderDao.listOrderById(Id);

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setId(order.getId());
        orderResponseDto.setProductId(order.getProductId());
        orderResponseDto.setProductName(order.getProductName());
        orderResponseDto.setUserId(order.getUserId());
        orderResponseDto.setUserName(order.getUserName());
        orderResponseDto.setPrice(order.getPrice());

        return orderResponseDto;
    }
}
