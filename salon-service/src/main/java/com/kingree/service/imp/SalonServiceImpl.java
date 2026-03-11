package com.kingree.service.imp;

import com.kingree.modal.Salon;
import com.kingree.payload.dto.SalonDTO;
import com.kingree.payload.dto.UserDTO;
import com.kingree.respository.SalonRepository;
import com.kingree.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO salonDTO, UserDTO userDTO) {
        Salon salon = new Salon();
        salon.setName(salonDTO.getName());
        salon.setAddress(salonDTO.getAddress());
        salon.setEmail(salonDTO.getEmail());
        salon.setCity(salonDTO.getCity());
        salon.setImages(salonDTO.getImages());
        salon.setOwnerId(userDTO.getId());
        salon.setOpenTime(salonDTO.getOpenTime());
        salon.setCloseTime(salonDTO.getCloseTime());
        salon.setPhoneNumber(salonDTO.getPhoneNumber());

        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salonDTO, UserDTO userDTO, Long id) throws Exception {
        Salon existingSalon = salonRepository.findById(id).orElse(null);

        if (existingSalon != null && existingSalon.getOwnerId().equals(userDTO.getId())) {
            existingSalon.setName(salonDTO.getName());
            existingSalon.setAddress(salonDTO.getAddress());
            existingSalon.setEmail(salonDTO.getEmail());
            existingSalon.setCity(salonDTO.getCity());
            existingSalon.setImages(salonDTO.getImages());
            existingSalon.setOwnerId(userDTO.getId());
            existingSalon.setOpenTime(salonDTO.getOpenTime());
            existingSalon.setCloseTime(salonDTO.getCloseTime());
            existingSalon.setPhoneNumber(salonDTO.getPhoneNumber());
        }

        throw new Exception("Salon not exist");
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long id) throws Exception {
        Salon salon = salonRepository.findById(id).orElse(null);
        if(salon == null) {
            throw new Exception("Salon not exist");
        }

        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}
