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
public interface ItemRepositoryJPA extends JpaRepository<ItemEntity, Long>, PagingAndSortingRepository<ItemEntity, Long> {
    @Query(value = "SELECT i FROM ItemEntity i where (:name is null or i.name like CONCAT('%',:name,'%')) and (:typeId is null or i.typeId = :typeId)")
    List<ItemEntity> getAllItem(Pageable pageable, String name, Long typeId);

    @Query(value = "SELECT count(i) FROM ItemEntity i where (:name is null or i.name like CONCAT('%',:name,'%')) and (:typeId is null or i.typeId = :typeId)")
    Long countItem(String name, Long typeId);
}
