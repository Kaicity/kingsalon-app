package com.kingree.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kingree.domain.PaymentMethod;
import com.kingree.modal.PaymentOrder;
import com.kingree.payload.dto.BookingDTO;
import com.kingree.payload.response.PaymentLinkResponse;
import com.kingree.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDTO bookingDTO,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt) throws Exception {

        PaymentLinkResponse paymentLinkResponse = paymentService.createOrder(jwt, bookingDTO, paymentMethod);

        return ResponseEntity.ok(paymentLinkResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable Long id) throws Exception {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderById(id);
        return ResponseEntity.ok(paymentOrder);
    }

    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPayment(@RequestParam Long paymentId)
            throws Exception {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderById(paymentId);
        Boolean res = paymentService.proceedPayment(paymentOrder, paymentId);
        return ResponseEntity.ok(res);
    }
}
