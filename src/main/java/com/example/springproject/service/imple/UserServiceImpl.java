package com.example.springproject.service.imple;

import com.example.springproject.dao.UserDao;
import com.example.springproject.dto.UserDto;
import com.example.springproject.dto.UserResponseDto;
import com.example.springproject.service.UserService;
import com.example.springproject.entity.Product;
import com.example.springproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<UserResponseDto> allUserOrderByName() {
        List<User> users = userDao.selectAllByOrderByPriceAsc();
        List<UserResponseDto> productResponseDtoList =
                users.stream().map(UserResponseDto::new).collect(Collectors.toList());;
        return productResponseDtoList;
    }

    @Override
    public List<UserResponseDto> allUser() {
        List<User> users = userDao.selectAllBy();
        List<UserResponseDto> productResponseDtoList =
                users.stream().map(UserResponseDto::new).collect(Collectors.toList());;
        return productResponseDtoList;
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userDao.selectUserByUid(id);

        UserDto userDto = new UserDto();
        userDto.setUid(user.getUid());
        userDto.setName(user.getName());

        return userDto;
    }
}
