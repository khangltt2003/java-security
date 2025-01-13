package com.dylan.spring_security_jwt.service;

import com.dylan.spring_security_jwt.exception.MyException;
import com.dylan.spring_security_jwt.entity.User;
import com.dylan.spring_security_jwt.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new MyException("cannot find user"));
    }

    public User getUserByVerificationCode(String verificationCode){
        return userRepository.findByVerificationCode(verificationCode).orElseThrow(()-> new MyException("cannot find user"));
    }
}
