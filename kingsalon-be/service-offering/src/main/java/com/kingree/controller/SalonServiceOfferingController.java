package com.kingree.controller;

import com.kingree.dto.CategoryDTO;
import com.kingree.dto.SalonDTO;
import com.kingree.dto.ServiceOfferingDTO;
import com.kingree.modal.ServiceOffering;
import com.kingree.service.ServiceOfferingService;
import com.kingree.service.client.CategoryFeignClient;
import com.kingree.service.client.SalonFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
@RequiredArgsConstructor
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;
    private final SalonFeignClient salonFeignClient;
    private final CategoryFeignClient categoryFeignClient;

    @PostMapping
    public ResponseEntity<ServiceOffering> createServiceOffering(
            @RequestBody ServiceOfferingDTO serviceOfferingDTO,
            @RequestHeader("Authorization") String jwt) throws Exception {

        SalonDTO salonDTO = salonFeignClient.getSalonByOwnerId(jwt).getBody();

        CategoryDTO categoryDTO = categoryFeignClient
                .getCategoryByIdAndSalon(salonDTO.getId(), serviceOfferingDTO.getCategoryId()).getBody();

        ServiceOffering serviceOffering = serviceOfferingService.createServiceOffering(salonDTO, serviceOfferingDTO,
                categoryDTO);

        return ResponseEntity.ok(serviceOffering);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateServiceOffering(
            @PathVariable Long id,
            @RequestBody ServiceOffering serviceOffering) throws Exception {

        ServiceOffering resUpdateServiceoffering = serviceOfferingService.updateServiceOffering(id, serviceOffering);

        return ResponseEntity.ok(resUpdateServiceoffering);

    }
}
