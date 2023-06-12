package com.example.springproject.service;

import com.example.springproject.dto.UserDto;
import com.example.springproject.dto.UserResponseDto;
import com.example.springproject.entity.Product;
import com.example.springproject.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponseDto> allUserOrderByName();
    List<UserResponseDto> allUser();
    UserDto getUserById(String id);
}
