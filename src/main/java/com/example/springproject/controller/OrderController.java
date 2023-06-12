package com.example.springproject.controller;

import com.example.springproject.config.security.JwtTokenProvider;
import com.example.springproject.dto.OrderDto;
import com.example.springproject.dto.OrderResponseDto;
import com.example.springproject.dto.ProductResponseDto;
import com.example.springproject.dto.UserDto;
import com.example.springproject.entity.Order;
import com.example.springproject.service.OrderService;
import com.example.springproject.service.ProductService;
import com.example.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final ProductService productService; // Product 테이블의 id, name 사용
    private final OrderService orderService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService, UserService userService, JwtTokenProvider jwtTokenProvider){
        this.orderService = orderService;
        this.productService = productService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public ResponseEntity<OrderResponseDto> requestOrder(HttpServletRequest request, @RequestParam Long productId
    ,@RequestParam String productName) throws Exception {
        String id = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        UserDto userDto = userService.getUserById(id);
        ProductResponseDto productResponseDto = productService.getProduct(productId);

        if(productResponseDto.getStock() == 0){
            return null;
        } else {
            OrderDto orderDto = new OrderDto();
            orderDto.setProductId(productId);
            orderDto.setProductName(productName);
            orderDto.setPrice(productResponseDto.getPrice());
            orderDto.setUserId(userDto.getUid());
            orderDto.setUserName(userDto.getName());
            OrderResponseDto orderResponseDto = orderService.requestOrder(orderDto);
            productService.changeProductStock(productResponseDto.getNumber(), productResponseDto.getName(), productResponseDto.getStock());
            return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
        }
    }
    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> listOrder(){
        List<OrderResponseDto> orderResponseDtos = orderService.listOrder();
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDtos);
    }
    @GetMapping("/listByUserId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> listOrderByUserId(String userId){
        List<OrderResponseDto> orderResponseDtos = orderService.listOrderByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDtos);
    }
    @GetMapping("/listByProductId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> listOrderByProductId(Long productId){
        List<OrderResponseDto> orderResponseDtos = orderService.listOrderByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDtos);
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderResponseDto> listOrderById(Long id){
        OrderResponseDto orderResponseDto = orderService.listOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }

}
