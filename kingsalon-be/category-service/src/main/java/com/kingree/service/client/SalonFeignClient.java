package com.kingree.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.kingree.payload.dto.SalonDTO;

@FeignClient("salon-service")
public interface SalonFeignClient {

    @GetMapping("/api/salons/owner")
    ResponseEntity<SalonDTO> getSalonByOwnerId(
            @RequestHeader("Authorization") String jwt) throws Exception;
}
