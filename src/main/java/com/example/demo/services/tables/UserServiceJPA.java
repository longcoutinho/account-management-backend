package com.example.demo.services.tables;

import com.example.demo.dtos.ResponseUserDTO;
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
        List<UserEntity> results = userRepositoryJPA.findByUsername(username);
        return !results.isEmpty();
    }

    public Object loginUser(UserDTO user) {
        List<UserEntity> listUsers = userRepositoryJPA.findByUsername(user.getUsername());
        if (listUsers.size() != 1) {
            throw new CustomException(ErrorApp.WRONG_LOGIN);
        }
        UserEntity results = listUsers.get(0);
        if (!passwordEncoder.matches(user.getPassword(), results.getPassword())) throw new CustomException(ErrorApp.WRONG_LOGIN);
        ResponseUserDTO responseUser = results.convertFromEntity();
        String token = jwtTokenProvider.generateToken(responseUser);
        System.out.println(token);
        String userId = jwtTokenProvider.getUserIdFromJWT(token);
        System.out.println(userId);
        return responseUser;
    }
}
