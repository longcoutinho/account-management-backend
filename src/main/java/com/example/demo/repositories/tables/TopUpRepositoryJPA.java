package com.example.demo.repositories.tables;
import com.example.demo.dtos.topup.TopUpResponseDTO;
import com.example.demo.repositories.tables.entities.TopUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopUpRepositoryJPA extends JpaRepository<TopUpEntity, Long> {

    @Query("select e from TopUpEntity e " +
            "where (:status is null or e.status = :status) and (:username is null or e.username = :username) and (:id is null or e.id = :id)" +
            "order by e.createDate desc")
    List<TopUpEntity> getAll(Long status, String username, String id);

    @Query("select count(e) from TopUpEntity e where (:status is null or e.status = :status) and (:username is null or e.username = :username) and (:id is null or e.id = :id)")
    Long getSumRequest(Long status, String username, String id);

    @Query("select sum(e.amount) from TopUpEntity e where (:status is null or e.status = :status) and (:username is null or e.username = :username) and (:id is null or e.id = :id)")
    Long getSumAmount(Long status, String username, String id);
}
