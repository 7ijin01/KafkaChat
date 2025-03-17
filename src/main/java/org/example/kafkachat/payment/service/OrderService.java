package org.example.kafkachat.payment.service;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.payment.entity.Orders;
import org.example.kafkachat.payment.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    // 주문 생성
    public Orders createOrder(String memberId) {
        String merchantUid = generateMerchantUid();
        Orders order = new Orders(memberId, merchantUid);
        return orderRepository.save(order);
    }

    // 주문번호 생성 메서드
    private String generateMerchantUid() {
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDay = today.format(formatter);
        return formattedDay + '-' + uniqueString;
    }
}
