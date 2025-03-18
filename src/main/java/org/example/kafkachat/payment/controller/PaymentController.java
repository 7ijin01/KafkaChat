package org.example.kafkachat.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.example.kafkachat.payment.dto.PaymentRequestDto;
import org.example.kafkachat.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

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

    // 아임포트 결제 검증 및 결제 완료 처리
    @PostMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> validateIamport(@PathVariable String imp_uid, @RequestBody PaymentRequestDto request) throws IamportResponseException, IOException {
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        log.info("✅ 아임포트 API 응답 전체: {}", payment.getResponse());

        // 🔍 JSON 문자열 변환 후 다시 확인 (JSON 파싱 문제 가능성 체크)
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(payment.getResponse());
        log.info("✅ JSON 변환된 아임포트 응답: {}", jsonResponse);

        // 🔍 `merchantUid` 직접 확인
        String merchantUidFromIamport = payment.getResponse().getMerchantUid();
        log.info("✅ 아임포트에서 받은 주문번호 (원본): {}", merchantUidFromIamport);

        // 🔍 주문번호 길이 확인
        log.info("✅ 주문번호 길이: {}", merchantUidFromIamport.length());


        paymentService.processPayment(payment.getResponse().getMerchantUid(), request.getMemberId());

        return payment;
    }
}
