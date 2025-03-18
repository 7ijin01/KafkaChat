package org.example.kafkachat.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "payment_history")
public class PaymentHistory {
    @Id
    private String paymentId; // MongoDB에서 자동 생성되는 ID

    @Field("member_id")
    private String memberId; // 결제한 사용자 ID

    @Field("order_id")
    private String orderId; // 결제된 주문 ID

    @Field("merchant_uid")
    private String merchantUid;

    @Field("total_price")
    private BigDecimal totalPrice = BigDecimal.valueOf(1); // 결제 금액 (100원 고정)

    @Field("paid_at")
    @CreatedDate
    private LocalDateTime paidAt; // 결제 시각

    @Field("status")
    private Boolean status = true; // 결제 상태 (기본값: true)

    public PaymentHistory(String memberId, String orderId) {
        this.memberId = memberId;
        this.orderId = orderId;
    }
    public static PaymentHistory createPaymentDto(String memberId, String orderId, String merchantUid)
    {
        return PaymentHistory.builder()
                .memberId(memberId)
                .orderId(orderId)
                .paidAt(LocalDateTime.now())
                .merchantUid(merchantUid)
                .build();
    }

}

