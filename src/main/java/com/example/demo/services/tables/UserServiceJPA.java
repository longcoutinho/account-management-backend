package com.example.demo.services.tables;

import com.example.demo.dtos.ResponseUserDTO;
import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repositories.tables.UserRepositoryJPA;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceJPA {
    @Autowired
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public List<UserEntity> findAll() {
        return userRepositoryJPA.findAll();
    }

    public Object createNewUser(UserDTO user) throws Exception {
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity newUser = new UserEntity(user);
        return userRepositoryJPA.save(newUser);
    }

    public void validateUser(UserDTO user) throws Exception {
        if (existUsername(user.getUsername())) throw new CustomException(ErrorApp.USERNAME_EXIST);
    }

    public Boolean existUsername(String username) {
        UserEntity results = userRepositoryJPA.findByUsername(username);
        return results != null;
    }

    public Object loginUser(UserDTO user) {
        UserEntity results = userRepositoryJPA.findByUsername(user.getUsername());
        if (!passwordEncoder.matches(user.getPassword(), results.getPassword())) throw new CustomException(ErrorApp.WRONG_LOGIN);
        ResponseUserDTO responseUser = results.convertFromEntity();
        responseUser.setAccessToken(jwtTokenProvider.generateToken(responseUser));
        return responseUser;
    }

    public Object addBalance(TopUpRequestDTO params) {
        UserEntity user = userRepositoryJPA.findByUsername(params.getUsername());
        user.setBalance(user.getBalance() + params.getAmount());
        userRepositoryJPA.save(user);
        return 1L;
    }

    public Object getUserById(String id) {
        return userRepositoryJPA.findByUserId(id);
    }

    public UserEntity findByUserId(String userId) {
        return userRepositoryJPA.findByUserId(userId);
    }
}
