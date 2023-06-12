package com.example.springproject.service;

import com.example.springproject.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto getProduct(Long number);
    List<ProductResponseDto> getProductByName(String name);
    ProductResponseDto saveProduct(String name, int price, int stock);
    ProductResponseDto changeProductName(Long number, String name, int price, int stock) throws Exception;

    ProductResponseDto changeProductStock(Long number, String name, int stock) throws Exception;
    Long countByPrice(Long price);
    ProductResponseDto getProductByNameANdPrice(String name, int price);
    List<ProductResponseDto> listProductByName(String name);
    List<ProductResponseDto> listProductByOrder();
    List<ProductResponseDto> listOrderByPrice();

    List<ProductResponseDto> listProductByNumber(Long number);
    List<ProductResponseDto> allProduct();
    boolean existsByNumber(Long number);

    void deleteProduct(long number) throws Exception;

}
