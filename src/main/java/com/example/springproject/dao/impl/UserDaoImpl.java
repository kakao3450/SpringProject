package com.example.springproject.dao.impl;

import com.example.springproject.dao.UserDao;
import com.example.springproject.entity.Product;
import com.example.springproject.entity.User;
import com.example.springproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Autowired
    public UserDaoImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> selectAllByOrderByPriceAsc() {
        return userRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<User> selectAllBy() {
        return userRepository.findAllBy();
    }

    @Override
    public User selectUserByUid(String uid) {
        return userRepository.getByUid(uid);
    }
}
