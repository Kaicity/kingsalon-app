package com.kingree.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kingree.payload.dto.SalonDTO;
import com.kingree.service.SalonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;

    @PatchMapping("/{id}")
    public ResponseEntity<SalonDTO> updateSalon(
            @RequestBody SalonDTO salonDTO,
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(salonService.updateSalon(jwt, salonDTO, id));
    }

    @GetMapping
    public ResponseEntity<List<SalonDTO>> getSalons() {
        return ResponseEntity.ok(salonService.getAllSalons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(salonService.getSalonById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(@RequestParam String city) {
        return ResponseEntity.ok(salonService.searchSalonByCity(city));
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(
            @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(salonService.getSalonByOwnerId(jwt));
    }
}
