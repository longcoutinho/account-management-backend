package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.UserAdminEntity;
import com.example.demo.repositories.tables.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAdminRepositoryJPA extends JpaRepository<UserAdminEntity, Long> {
    List<UserAdminEntity> findByUsername(String username);
}
