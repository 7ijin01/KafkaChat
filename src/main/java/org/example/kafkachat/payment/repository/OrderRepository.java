package org.example.kafkachat.payment.repository;

import org.example.kafkachat.payment.entity.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Orders, String> {
    Optional<Orders> findByMerchantUid(String merchantUid);
}

