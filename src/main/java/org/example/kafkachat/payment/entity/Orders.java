package org.example.kafkachat.payment.entity;

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
@Document(collection = "orders")
public class Orders {
    @Id
    private String orderId; // MongoDB에서 자동 생성되는 ID (ObjectId)

    @Field("member_id")
    private String memberId; // 주문한 사용자 ID (MongoDB의 ObjectId)

    @Field("total_price")
    private BigDecimal totalPrice = BigDecimal.valueOf(1); // 결제 금액 (1원 고정)

    @Field("payment_status")
    private Boolean paymentStatus = false; // 결제 상태 (기본값: 미결제)

    @Field("merchant_uid")
    private String merchantUid; // 주문번호 (아임포트와 연동)

    @Field("created_at")
    @CreatedDate
    private LocalDateTime createdAt; // 주문 생성 시간

    public Orders(String memberId, String merchantUid) {
        this.memberId = memberId;
        this.merchantUid = merchantUid;
    }
}

