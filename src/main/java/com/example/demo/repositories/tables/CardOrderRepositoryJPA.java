package com.example.demo.repositories.tables;

import com.example.demo.dtos.CountAllOrderDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.tables.entities.CardOrderEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CardOrderRepositoryJPA extends JpaRepository<CardOrderEntity, String> {
    @Query("select e from CardOrderEntity e " +
            "where ((e.createDate >= :fromDate and e.createDate < :toDate) or (:fromDate is null and :toDate is null))" +
            "and (e.createUser = :username or :username is null) " +
            "and e.status != 'PENDING' " +
            "and (e.status = :status or :status is null)" +
            "and (e.id = :transId or :transId is null)" +
            "order by e.createDate desc")
    List<Object> findAllByRequest(Date fromDate, Date toDate, String status, String username, String transId, Pageable pageable);

    @Query(value = "select count(1) as total_amount " +
            "from card_orders e " +
            "where ((e.create_date >= :fromDate and e.create_date < :toDate) or (:fromDate is null and :toDate is null))" +
            "and (e.create_user = :username or :username is null) " +
            "and e.status != 'PENDING' " +
            "and (e.status = :status or :status is null)" +
            "and (e.id = :transId or :transId is null)" +
            "order by e.create_date desc", nativeQuery = true)
    Long countTotalByRequest(Date fromDate, Date toDate, String status, String username, String transId);

    @Query(value = "select sum(e.price) as total_amount " +
            "from card_orders e " +
            "where ((e.create_date >= :fromDate and e.create_date < :toDate) or (:fromDate is null and :toDate is null))" +
            "and (e.create_user = :username or :username is null) " +
            "and e.status != 'PENDING' " +
            "and (e.status = :status or :status is null)" +
            "and (e.id = :transId or :transId is null)" +
            "order by e.create_date desc", nativeQuery = true)
    Long countToTalRevenue(Date fromDate, Date toDate, String status, String username, String transId);
}
