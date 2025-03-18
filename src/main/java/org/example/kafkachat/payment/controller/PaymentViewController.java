package org.example.kafkachat.payment.controller;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.payment.entity.Orders;
import org.example.kafkachat.payment.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentViewController {
    private final OrderService orderService;


    @GetMapping("/order/{memberId}")
    public String getOrderPage(@PathVariable String memberId, Model model) {
        Orders order = orderService.createOrder(memberId);

        model.addAttribute("impUserCode", "imp74838410"); // 아임포트 사용자 코드
        model.addAttribute("merchantUid", order.getMerchantUid());
        model.addAttribute("productName", "테스트 상품");
        model.addAttribute("totalPrice", order.getTotalPrice());
        model.addAttribute("memberId", memberId);
        model.addAttribute("orderId", order.getOrderId());

        return "order"; // order.mustache 파일 반환
    }
}
