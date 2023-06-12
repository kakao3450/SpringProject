package com.example.springproject.repository;

import com.example.springproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User getByUid(String uId);
    List<User> findAllByOrderByNameAsc();
    List<User> findAllBy();
}
