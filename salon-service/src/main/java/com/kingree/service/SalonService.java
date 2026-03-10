package com.kingree.service;

import com.kingree.modal.Salon;
import com.kingree.payload.dto.SalonDTO;
import com.kingree.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {
    Salon createSalon(SalonDTO salonDTO, UserDTO userDTO);

    Salon updateSalon(SalonDTO salonDTO, UserDTO userDTO, Long id);

    List<Salon> getAllSalons();

    Salon getSalonById(Long id);

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> searchSalonByCity(String city);
}
