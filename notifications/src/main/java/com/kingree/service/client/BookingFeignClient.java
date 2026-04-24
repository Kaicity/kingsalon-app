package com.kingree.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kingree.payload.dto.BookingDTO;

@FeignClient("booking-service")
public interface BookingFeignClient {

    @GetMapping("/api/bookings/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) throws Exception;
}
