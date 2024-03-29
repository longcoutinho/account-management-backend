package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findByUserId(String userId);

    List<UserEntity> findByRole(String role);
}
