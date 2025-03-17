package org.example.kafkachat.payment.controller;

import lombok.RequiredArgsConstructor;
import org.example.kafkachat.payment.entity.Orders;
import org.example.kafkachat.payment.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // 주문 생성 (100원 결제용)
    @PostMapping("/create/{memberId}")
    public ResponseEntity<Orders> createOrder(@PathVariable String memberId) {
        Orders order = orderService.createOrder(memberId);
        return ResponseEntity.ok(order);
    }
}

