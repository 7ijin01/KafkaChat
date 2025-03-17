package org.example.kafkachat.payment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
public class PaymentPageController {

    @Value("${spring.IMP_API_KEY}")
    private String apiKey;
    @Value("${spring.IMP_API_CHANNEL}") // 채널키가 필요하면 추가
    private String channelKey;

    @GetMapping("/payment")
    public String paymentPage(Model model) {
        String merchantUid = generateMerchantUid(); // 주문번호 생성

        model.addAttribute("apiKey", apiKey);
        model.addAttribute("channelKey", channelKey);
        model.addAttribute("merchantUid", merchantUid);
        model.addAttribute("totalPrice", 100);
        return "payment";
    }

    private String generateMerchantUid() {
        String uniqueString = UUID.randomUUID().toString().replace("-", "");
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return today.format(formatter) + '-' + uniqueString;
    }
}
