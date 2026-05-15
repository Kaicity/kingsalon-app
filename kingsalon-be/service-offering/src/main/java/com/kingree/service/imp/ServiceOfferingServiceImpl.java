package com.kingree.service.imp;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kingree.modal.ServiceOffering;
import com.kingree.payload.dto.CategoryDTO;
import com.kingree.payload.dto.SalonDTO;
import com.kingree.payload.dto.ServiceOfferingDTO;
import com.kingree.repository.ServiceOfferingRepository;
import com.kingree.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createServiceOffering(SalonDTO salonDTO, ServiceOfferingDTO serviceOfferingDTO,
            CategoryDTO categoryDTO) {
        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setName(serviceOfferingDTO.getName());
        serviceOffering.setImage(serviceOfferingDTO.getImage());
        serviceOffering.setDuration(serviceOfferingDTO.getDuration());
        serviceOffering.setPrice(serviceOfferingDTO.getPrice());
        serviceOffering.setDescription(serviceOfferingDTO.getDescription());
        serviceOffering.setCategoryId(categoryDTO.getId());
        serviceOffering.setSalonId(salonDTO.getId());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateServiceOffering(Long id, ServiceOffering serviceOffering) throws Exception {
        ServiceOffering updateData = serviceOfferingRepository.findById(id).orElse(null);

        if (updateData == null) {
            throw new Exception("Service not exist with id " + id);
        }

        updateData.setName(serviceOffering.getName());
        updateData.setImage(serviceOffering.getImage());
        updateData.setDuration(serviceOffering.getDuration());
        updateData.setPrice(serviceOffering.getPrice());
        updateData.setDescription(serviceOffering.getDescription());

        return serviceOfferingRepository.save(updateData);
    }

    @Override
    public Page<ServiceOffering> getAllServicesOfferingBySalonId(Long salonId, Long categoryId, Pageable pageable) {

        if (categoryId != null) {
            return serviceOfferingRepository.findBySalonIdAndCategoryId(salonId, categoryId, pageable);
        }

        return serviceOfferingRepository.findBySalonId(salonId, pageable);
    }

    @Override
    public Page<ServiceOffering> getServicesOfferingByIds(Set<Long> ids, Pageable pageable) {
        List<ServiceOffering> services = serviceOfferingRepository.findAllById(ids);

        return new PageImpl<>(services);
    }

    @Override
    public ServiceOffering getServiceOfferingById(Long id) throws Exception {
        ServiceOffering existing = serviceOfferingRepository.findById(id).orElse(null);

        if (existing == null) {
            throw new Exception("Service not exist with id " + id);
        }

        return existing;
    }
}
