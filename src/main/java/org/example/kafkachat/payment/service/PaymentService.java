package org.example.kafkachat.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kafkachat.payment.entity.PaymentHistory;
import org.example.kafkachat.payment.repository.PaymentHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentService {
    private final PaymentHistoryRepository paymentHistoryRepository;

    // 1원 결제 처리
    public PaymentHistory processPayment(String merchantUid, String memberId) {
        log.info("✅ 결제 처리 시작: merchantUid={}, memberId={}", merchantUid, memberId);

        PaymentHistory paymentHistory = PaymentHistory.builder()
                .merchantUid(merchantUid)
                .memberId(memberId)
                .totalPrice(BigDecimal.valueOf(1)) // 1원 고정
                .paidAt(LocalDateTime.now())
                .status(true)
                .build();

        return paymentHistoryRepository.save(paymentHistory);
    }
}
