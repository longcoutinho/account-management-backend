package com.example.demo.kafka;
import com.example.demo.services.tables.SaleOrderServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    SaleOrderServiceJPA saleOrderServiceJPA;

    @KafkaListener(topics = "order", groupId = "order-group")
    public void listenOrderMessage(String orderId) {
        saleOrderServiceJPA.processOrder(orderId);
    }
}
