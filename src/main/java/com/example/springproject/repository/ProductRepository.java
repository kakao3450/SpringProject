package com.example.springproject.repository;

import com.example.springproject.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByNumber(Long number);
    List<Product> findByName(String name, Sort sort);
    Long countByPrice(int price);

    boolean existsByNumber(Long number);
    Product findAllByNameAndPrice(String name, int price);

    List findByNameOrderByPriceAsc(String name);
    List<Product> findAllByOrderByPriceDesc();
    List<Product> findAllBy();

    @Query("SELECT p FROM Product AS p WHERE p.stock =:stock")
    List<Product> listByStock(@Param("stock")int stock);

}
