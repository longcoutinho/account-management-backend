package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByUsername(String username);
}
