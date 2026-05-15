package com.kingree.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kingree.payload.dto.SalonDTO;

public interface SalonService {
    SalonDTO createSalon(String jwt, SalonDTO salonDTO) throws Exception;

    SalonDTO updateSalon(String jwt, SalonDTO salonDTO, Long id) throws Exception;

    Page<SalonDTO> getAllSalons();

    SalonDTO getSalonById(Long id) throws Exception;

    SalonDTO getSalonByOwnerId(String jwt) throws Exception;

    Page<SalonDTO> searchSalonByCity(String keyword, Pageable pageable);
}
