package com.kingree.service;

import com.kingree.dto.CategoryDTO;
import com.kingree.dto.SalonDTO;
import com.kingree.dto.ServiceOfferingDTO;
import com.kingree.modal.ServiceOffering;

import java.util.Set;

public interface ServiceOfferingService {
    ServiceOffering createServiceOffering(SalonDTO salonDTO, ServiceOfferingDTO serviceOfferingDTO, CategoryDTO categoryDTO);

    ServiceOffering updateServiceOffering(Long id, ServiceOffering serviceOffering);

    Set<ServiceOffering> getAllServicesOfferingBySalonId(Long salonId, Long categoryId);

    Set<ServiceOffering> getServicesOfferingByIds(Set<Long> ids);
}
