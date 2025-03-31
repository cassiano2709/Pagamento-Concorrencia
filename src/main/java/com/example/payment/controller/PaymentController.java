package com.example.payment.controller;

import com.example.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/{id}/deduct")
    public String deductPayment(@PathVariable Long id, @RequestParam BigDecimal amount) {
        service.simulateConcurrentPayments(id, amount);
        return "Processing concurrent payments...";
    }
}
