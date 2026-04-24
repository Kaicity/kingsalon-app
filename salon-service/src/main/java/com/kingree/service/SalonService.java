package com.kingree.service;

import java.util.List;

import com.kingree.payload.dto.SalonDTO;

public interface SalonService {
    SalonDTO createSalon(String jwt, SalonDTO salonDTO) throws Exception;

    SalonDTO updateSalon(String jwt, SalonDTO salonDTO, Long id) throws Exception;

    List<SalonDTO> getAllSalons();

    SalonDTO getSalonById(Long id) throws Exception;

    SalonDTO getSalonByOwnerId(String jwt) throws Exception;

    List<SalonDTO> searchSalonByCity(String city);
}
