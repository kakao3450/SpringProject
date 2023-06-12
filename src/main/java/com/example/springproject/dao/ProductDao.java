package com.example.springproject.dao;


import com.example.springproject.entity.Product;

import java.util.List;

public interface ProductDao {
    Product insertProduct(Product product);
    Product selectProduct(Long number);
    List<Product> selectProductName(String Name);
    Product selectProductByNameAndPrice(String name, int price);
    Long countByPrice(int price);
    boolean existsByNumber(Long number);
    List<Product> listProductByName(String name);
    List<Product> listProductByPrice();

    List<Product> listProductByOrder();
    List<Product> allProduct();
    Product updateProductName(Long number, String name, int price, int stock) throws Exception;
    Product updateProductStock(Long number, String name, int stock) throws  Exception;
    List<Product> listProductByNumber(Long number);
    void deleteProduct(Long number) throws Exception;
}
