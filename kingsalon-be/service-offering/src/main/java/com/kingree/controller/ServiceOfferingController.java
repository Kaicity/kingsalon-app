package com.kingree.controller;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kingree.modal.ServiceOffering;
import com.kingree.payload.response.PageResponse;
import com.kingree.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<PageResponse<ServiceOffering>> getServicesOfferingBySalonId(@PathVariable Long salonId,
            @RequestParam(required = false) Long categoryId, Pageable pageable) {

        Page<ServiceOffering> serviceOfferings = serviceOfferingService.getAllServicesOfferingBySalonId(salonId,
                categoryId, pageable);

        return ResponseEntity
                .ok(PageResponse.from(serviceOfferings));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceOfferingById(@PathVariable Long id) throws Exception {
        ServiceOffering serviceOffering = serviceOfferingService.getServiceOfferingById(id);
        return ResponseEntity.ok(serviceOffering);
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<PageResponse<ServiceOffering>> getServiceByIds(@PathVariable Set<Long> ids, Pageable pageable)
            throws Exception {

        Page<ServiceOffering> serviceOfferings = serviceOfferingService.getServicesOfferingByIds(ids, pageable);

        return ResponseEntity.ok(PageResponse.from(serviceOfferings));
    }

}
