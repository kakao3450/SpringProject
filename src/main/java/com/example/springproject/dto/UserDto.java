package com.example.springproject.dto;

import com.example.springproject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String uid;
    private String password;
    private String name;
    private String email;
    private List<String> roles = new ArrayList<>();

}
