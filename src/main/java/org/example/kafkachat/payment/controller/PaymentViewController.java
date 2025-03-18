package org.example.kafkachat.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentViewController {

    @GetMapping("/order/{memberId}")
    public String getOrderPage(@PathVariable String memberId, Model model) {
        String merchantUid = generateMerchantUid();

        model.addAttribute("impUserCode", "imp74838410"); // 아임포트 사용자 코드
        model.addAttribute("merchantUid", merchantUid);
        model.addAttribute("productName", "테스트 상품");
        model.addAttribute("totalPrice", 1); // 1원 고정
        model.addAttribute("memberId", memberId);

        return "order";
    }

    private String generateMerchantUid()
    {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + '-' + UUID.randomUUID().toString().replace("-", "");
    }
}
