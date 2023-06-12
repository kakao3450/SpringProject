package com.example.springproject.repository;

import com.example.springproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QProductRepository extends JpaRepository<Product,Long>, QuerydslPredicateExecutor<Product> {
}
