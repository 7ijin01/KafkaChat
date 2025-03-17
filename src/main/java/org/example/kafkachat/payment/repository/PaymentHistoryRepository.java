package org.example.kafkachat.payment.repository;

import org.example.kafkachat.payment.entity.PaymentHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentHistoryRepository extends MongoRepository<PaymentHistory, String> {
    List<PaymentHistory> findByMemberId(String memberId);
}

