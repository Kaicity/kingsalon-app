package com.kingree.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.kingree.domain.PaymentMethod;
import com.kingree.dto.BookingDTO;
import com.kingree.dto.PaymentLinkResponse;

@FeignClient("payment-service")
public interface PaymentFeignClient {
    @PostMapping("/api/payments")
    ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestBody BookingDTO bookingDTO,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt);
}
