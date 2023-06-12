package com.example.springproject.dao;


import com.example.springproject.entity.Product;
import com.example.springproject.entity.User;

import java.util.List;

public interface UserDao {
    List<User> selectAllByOrderByPriceAsc();
    List<User> selectAllBy();

    User selectUserByUid(String uid);
}
