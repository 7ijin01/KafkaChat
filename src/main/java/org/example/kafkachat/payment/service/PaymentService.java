package org.example.kafkachat.payment.service;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.payment.entity.Orders;
import org.example.kafkachat.payment.entity.PaymentHistory;
import org.example.kafkachat.payment.repository.OrderRepository;
import org.example.kafkachat.payment.repository.PaymentHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.example.kafkachat.payment.entity.PaymentHistory.createPaymentDto;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {
    private final OrderRepository orderRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;

    // 결제 완료 처리
    public PaymentHistory processPayment(String merchantUid, String memberId) {
        Orders order = orderRepository.findByMerchantUid(merchantUid)
                .orElseThrow(() -> new NoSuchElementException("주문 정보를 찾을 수 없습니다."));

        order.setPaymentStatus(true);
        orderRepository.save(order);

        PaymentHistory paymentHistory = new PaymentHistory(memberId, order.getOrderId());
        return paymentHistoryRepository.save(paymentHistory);
    }
    public PaymentHistory createPayment(String memberId,String merchantUid) {
        Orders order = orderRepository.findByMerchantUid(merchantUid)
                .orElseThrow(() -> new NoSuchElementException("주문 정보를 찾을 수 없습니다."));
        PaymentHistory paymentHistory =createPaymentDto(memberId, order.getOrderId(), merchantUid);

        return paymentHistoryRepository.save(paymentHistory);
    }
}
