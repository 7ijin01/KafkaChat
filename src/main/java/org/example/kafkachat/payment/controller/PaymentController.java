package org.example.kafkachat.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kafkachat.payment.dto.PaymentRequestDto;
import org.example.kafkachat.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @Value("${spring.IMP_API_KEY}")
    private String apiKey;

    @Value("${spring.IMP_API_SECRET}")
    private String secretKey;

    private IamportClient iamportClient;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }

    @PostMapping("/verify/{imp_uid}")
    public ResponseEntity<?> validateIamport(@PathVariable String imp_uid, @RequestBody PaymentRequestDto request) {
        try {
            IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
            log.info("✅ 아임포트 API 응답 전체: {}", payment.getResponse());

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(payment.getResponse());
            log.info("✅ JSON 변환된 아임포트 응답: {}", jsonResponse);

            String merchantUidFromIamport = payment.getResponse().getMerchantUid();
            log.info("✅ 아임포트에서 받은 주문번호: {}", merchantUidFromIamport);

            paymentService.processPayment(merchantUidFromIamport, request.getMemberId());

            return ResponseEntity.ok(payment);

        } catch (IamportResponseException e) {
            log.error("❌ 아임포트 결제 검증 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().body("아임포트 결제 검증 실패: " + e.getMessage());
        } catch (IOException e) {
            log.error("❌ JSON 변환 오류: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("서버 오류 발생: " + e.getMessage());
        }
    }
}
