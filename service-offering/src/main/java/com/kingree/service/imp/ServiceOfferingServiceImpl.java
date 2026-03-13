package com.kingree.service.imp;

import com.kingree.dto.CategoryDTO;
import com.kingree.dto.SalonDTO;
import com.kingree.dto.ServiceOfferingDTO;
import com.kingree.modal.ServiceOffering;
import com.kingree.repository.ServiceOfferingRepository;
import com.kingree.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    @Autowired
    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createServiceOffering(SalonDTO salonDTO, ServiceOfferingDTO serviceOfferingDTO, CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public ServiceOffering updateServiceOffering(Long id, ServiceOffering serviceOffering) {
        return null;
    }

    @Override
    public Set<ServiceOffering> getAllServicesOfferingBySalonId(Long salonId, Long categoryId) {
        return Set.of();
    }

    @Override
    public Set<ServiceOffering> getServicesOfferingByIds(Set<Long> ids) {
        return Set.of();
    }
}
