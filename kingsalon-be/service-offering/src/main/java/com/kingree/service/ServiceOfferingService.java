package com.kingree.service;

import com.kingree.modal.ServiceOffering;
import com.kingree.payload.dto.CategoryDTO;
import com.kingree.payload.dto.SalonDTO;
import com.kingree.payload.dto.ServiceOfferingDTO;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceOfferingService {
    ServiceOffering createServiceOffering(SalonDTO salonDTO, ServiceOfferingDTO serviceOfferingDTO,
            CategoryDTO categoryDTO);

    ServiceOffering updateServiceOffering(Long id, ServiceOffering serviceOffering) throws Exception;

    Page<ServiceOffering> getAllServicesOfferingBySalonId(Long salonId, Long categoryId, Pageable pageable);

    Page<ServiceOffering> getServicesOfferingByIds(Set<Long> ids, Pageable pageable);

    ServiceOffering getServiceOfferingById(Long id) throws Exception;
}
