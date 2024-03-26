package com.example.demo.repositories.tables;
import com.example.demo.repositories.tables.entities.ItemEntity;
import com.example.demo.repositories.tables.entities.ItemTypeEntity;
import com.example.demo.repositories.tables.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ItemRepositoryJPA extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findByCardId(Long id);
}
