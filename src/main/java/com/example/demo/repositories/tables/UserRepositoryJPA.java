package com.example.demo.repositories.tables;
import com.example.demo.dtos.user.RequestUserDTO;
import com.example.demo.repositories.tables.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByUserId(String userId);

    @Query("select e from UserEntity e " +
            "where (:username is null or e.username = :username) " +
            "order by e.createDate desc")
    List<UserEntity> getAll(String username);
}
