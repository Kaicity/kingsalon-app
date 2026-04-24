package com.kingree.service.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kingree.dto.CategoryDTO;
import com.kingree.dto.SalonDTO;
import com.kingree.dto.ServiceOfferingDTO;
import com.kingree.modal.ServiceOffering;
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
    public Set<ServiceOffering> getAllServicesOfferingBySalonId(Long salonId, Long categoryId) {
        Set<ServiceOffering> services = serviceOfferingRepository.findBySalonId(salonId);

        if (categoryId != null) {
            services = services.stream().filter((service) -> service.getCategoryId() != null
                    && service.getCategoryId() == categoryId).collect(Collectors.toSet());
        }

        return services;
    }

    @Override
    public Set<ServiceOffering> getServicesOfferingByIds(Set<Long> ids) {
        List<ServiceOffering> services = serviceOfferingRepository.findAllById(ids);

        return new HashSet<>(services);
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
