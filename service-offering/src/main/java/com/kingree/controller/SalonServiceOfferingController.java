package com.kingree.controller;

import com.kingree.dto.CategoryDTO;
import com.kingree.dto.SalonDTO;
import com.kingree.dto.ServiceOfferingDTO;
import com.kingree.modal.ServiceOffering;
import com.kingree.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
@RequiredArgsConstructor
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createServiceOffering(@RequestBody ServiceOfferingDTO serviceOfferingDTO) {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceOfferingDTO.getCategoryId());

        ServiceOffering serviceOffering = serviceOfferingService.createServiceOffering(salonDTO, serviceOfferingDTO, categoryDTO);

        return ResponseEntity.ok(serviceOffering);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateServiceOffering(@PathVariable("id") Long id, @RequestBody ServiceOffering serviceOffering) throws Exception {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);

        ServiceOffering resUpdateServiceoffering = serviceOfferingService.updateServiceOffering(id, serviceOffering);

        return ResponseEntity.ok(resUpdateServiceoffering);

    }
}
