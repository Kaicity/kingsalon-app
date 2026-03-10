package com.kingree.service.imp;

import com.kingree.modal.Salon;
import com.kingree.payload.dto.SalonDTO;
import com.kingree.payload.dto.UserDTO;
import com.kingree.service.SalonService;

import java.util.List;

public class SalonServiceImpl implements SalonService {
    @Override
    public Salon createSalon(SalonDTO salonDTO, UserDTO userDTO) {
        return null;
    }

    @Override
    public Salon updateSalon(SalonDTO salonDTO, UserDTO userDTO, Long id) {
        return null;
    }

    @Override
    public List<Salon> getAllSalons() {
        return List.of();
    }

    @Override
    public Salon getSalonById(Long id) {
        return null;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return null;
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return List.of();
    }
}
