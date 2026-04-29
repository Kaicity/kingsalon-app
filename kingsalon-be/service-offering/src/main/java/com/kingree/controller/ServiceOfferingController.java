package com.kingree.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kingree.modal.ServiceOffering;
import com.kingree.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesOfferingBySalonId(@PathVariable Long salonId,
            @RequestParam(required = false) Long categoryId) {
        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getAllServicesOfferingBySalonId(salonId,
                categoryId);
        return ResponseEntity.ok((serviceOfferings));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceOfferingById(@PathVariable Long id) throws Exception {
        ServiceOffering serviceOffering = serviceOfferingService.getServiceOfferingById(id);
        return ResponseEntity.ok(serviceOffering);
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServiceByIds(@PathVariable Set<Long> ids) throws Exception {
        Set<ServiceOffering> serviceOffering = serviceOfferingService.getServicesOfferingByIds(ids);
        return ResponseEntity.ok(serviceOffering);
    }

}
