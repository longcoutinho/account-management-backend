package com.example.demo.services.tables;

import com.example.demo.dtos.ResponseUserDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.repositories.tables.UserAdminRepositoryJPA;
import com.example.demo.repositories.tables.entities.UserAdminEntity;
import com.example.demo.repositories.tables.entities.UserEntity;
import com.example.demo.utils.enums.ErrorApp;
import com.example.demo.utils.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdminServiceJPA {
    @Autowired
    UserAdminRepositoryJPA userAdminRepositoryJPA;

    public Object loginUser(UserDTO user) {
        List<UserAdminEntity> listUsers = userAdminRepositoryJPA.findByUsername(user.getUsername());
        if (listUsers.size() != 1) {
            throw new CustomException(ErrorApp.WRONG_LOGIN);
        }
        UserAdminEntity results = listUsers.get(0);
        if (!user.getPassword().equals(results.getPassword())) throw new CustomException(ErrorApp.WRONG_LOGIN);
        results.setPassword(null);
        return results;
    }
}
