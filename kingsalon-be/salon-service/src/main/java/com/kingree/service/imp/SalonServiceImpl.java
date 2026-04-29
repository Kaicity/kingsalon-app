package com.kingree.service.imp;

import com.kingree.mapper.SalonMapper;
import com.kingree.modal.Salon;
import com.kingree.payload.dto.SalonDTO;
import com.kingree.payload.dto.UserDTO;
import com.kingree.repository.SalonRepository;
import com.kingree.service.SalonService;
import com.kingree.service.client.UserFeignClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonMapper salonMapper;

    private final SalonRepository salonRepository;

    private final UserFeignClient userFeignClient;

    @Override
    public SalonDTO createSalon(String jwt, SalonDTO salonDTO) throws Exception {

        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        // Check user owner only 1 by salon
        Salon existingSalonOwner = salonRepository.findByOwnerId(userDTO.getId());
        if (existingSalonOwner != null) {
            throw new Exception("Owner already has a salon");
        }

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

        return salonMapper.mapToDTO(salonRepository.save(salon));
    }

    @Override
    public SalonDTO updateSalon(String jwt, SalonDTO salonDTO, Long id) throws Exception {

        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        Salon existingSalon = salonRepository.findById(id).orElse(null);

        if (!salonDTO.getOwnerId().equals(userDTO.getId())) {
            throw new Exception("You don't have permission to update this salon");
        }

        if (existingSalon != null) {
            existingSalon.setName(salonDTO.getName());
            existingSalon.setAddress(salonDTO.getAddress());
            existingSalon.setEmail(salonDTO.getEmail());
            existingSalon.setCity(salonDTO.getCity());
            existingSalon.setImages(salonDTO.getImages());
            existingSalon.setOwnerId(userDTO.getId());
            existingSalon.setOpenTime(salonDTO.getOpenTime());
            existingSalon.setCloseTime(salonDTO.getCloseTime());
            existingSalon.setPhoneNumber(salonDTO.getPhoneNumber());

            return salonMapper.mapToDTO(salonRepository.save(existingSalon));
        }

        throw new Exception("Salon not exist");
    }

    @Override
    public List<SalonDTO> getAllSalons() {
        List<Salon> salons = salonRepository.findAll();

        List<SalonDTO> salonDTOS = salons.stream().map((salon) -> {
            SalonDTO salonDTO = salonMapper.mapToDTO(salon);
            return salonDTO;
        }).toList();

        return salonDTOS;
    }

    @Override
    public SalonDTO getSalonById(Long id) throws Exception {
        Salon salon = salonRepository.findById(id).orElse(null);
        if (salon == null) {
            throw new Exception("Salon not exist");
        }

        return salonMapper.mapToDTO(salon);
    }

    @Override
    public SalonDTO getSalonByOwnerId(String jwt) throws Exception {
        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        if (userDTO == null) {
            throw new Exception("User not found from jwt...");
        }

        return salonMapper.mapToDTO(salonRepository.findByOwnerId(userDTO.getId()));
    }

    @Override
    public List<SalonDTO> searchSalonByCity(String city) {
        List<Salon> salons = salonRepository.searchSalons(city);

        List<SalonDTO> salonDTOS = salons.stream().map((salon) -> {
            SalonDTO salonDTO = salonMapper.mapToDTO(salon);
            return salonDTO;
        }).toList();

        return salonDTOS;
    }
}
