package com.kingree.repository;

import com.kingree.modal.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, Long> {

    Set<ServiceOffering> findBySalonId(Long salonId);

    Set<ServiceOffering> findByCategoryId(Long categoryId);
}
