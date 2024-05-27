package com.example.demo.services.tables;

import com.example.demo.dtos.ResponseUserDTO;
import com.example.demo.dtos.TopUpRequestDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.dtos.user.AdjustBalanceDTO;
import com.example.demo.dtos.user.RequestUserDTO;
import com.example.demo.dtos.user.ResetPasswordDTO;
import com.example.demo.services.security.jwt.JwtTokenProvider;
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
        user.setRole(UserEntity.Role.USER.value);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity newUser = new UserEntity(user);
        userRepositoryJPA.save(newUser);
        return 1L;
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

        // username not exist
        if (results == null) {
            throw new CustomException(ErrorApp.WRONG_LOGIN);
        }

        // wrong pass
        if (!passwordEncoder.matches(user.getPassword(), results.getPassword())) {
            throw new CustomException(ErrorApp.WRONG_LOGIN);
        }

        // wrong role
        if (!user.getRole().equals(results.getRole())) {
            throw new CustomException(ErrorApp.WRONG_LOGIN);
        }

        // gen token and response
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

    public Object getAll(RequestUserDTO params) {
        return userRepositoryJPA.getAll(params.getUsername());
    }

    public Object findByUsername(String username) {
        return userRepositoryJPA.findByUsername(username);
    }

    public Object resetPassword(ResetPasswordDTO params) {
        UserEntity user = userRepositoryJPA.findByUsername(params.getUsername());
        user.setPassword(passwordEncoder.encode(params.getPassword()));
        userRepositoryJPA.save(user);
        return 1L;
    }

    public Object adjustBalance(AdjustBalanceDTO params) {
        UserEntity user = userRepositoryJPA.findByUsername(params.getUsername());
        Long balanceAfter = user.getBalance() + params.getAmount();
        if (balanceAfter < 0) throw new CustomException(ErrorApp.INSUFFICIENT_BALANCE);
        user.setBalance(user.getBalance() + params.getAmount());
        userRepositoryJPA.save(user);
        return 1L;
    }
}
