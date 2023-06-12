package com.example.springproject.service;

import com.example.springproject.dto.SignInResultDto;
import com.example.springproject.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String name, String email, String role);
    SignInResultDto signIn(String id, String password) throws RuntimeException;

}
