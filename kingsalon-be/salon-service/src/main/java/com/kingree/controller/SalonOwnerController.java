package com.kingree.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingree.payload.dto.SalonDTO;
import com.kingree.service.SalonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/salons/salon-owner")
@RequiredArgsConstructor
public class SalonOwnerController {

    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(
            @RequestBody SalonDTO salonDTO,
            @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(salonService.createSalon(jwt, salonDTO));
    }
}
