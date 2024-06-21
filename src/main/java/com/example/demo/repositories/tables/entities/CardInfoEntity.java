package com.example.demo.repositories.tables.entities;

import com.example.demo.services.tables.item.CardInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CARD_INFOS")
public class CardInfoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "ORDER_DETAIL_ID")
    String orderDetailId;

    @Column(name = "ITEM_ID")
    Long itemId;

    @Column(name = "CODE")
    String code;

    @Column(name = "SERIAL")
    String serial;

    @Column(name = "VENDOR")
    String vendor;

    @Column(name = "VALUE")
    int value;

    @Column(name = "EXPIRY")
    String expiry;

    @Column(name = "CREATE_DATE")
    Date createDate;

    public static List<CardInfo> covertToListDTO(List<CardInfoEntity> list) {
        List<CardInfo> res = new LinkedList<>();
        for(CardInfoEntity cardInfoEntity: list) {
            res.add(new CardInfo(cardInfoEntity.getCode(), cardInfoEntity.getSerial(), cardInfoEntity.getVendor(),
                    cardInfoEntity.getValue(), cardInfoEntity.getExpiry()));
        }
        return res;
    }
}
