package com.kingree.controller;

import com.kingree.mapper.SalonMapper;
import com.kingree.modal.Salon;
import com.kingree.payload.dto.SalonDTO;
import com.kingree.payload.dto.UserDTO;
import com.kingree.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    @Autowired
    private SalonMapper salonMapper;

    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon = salonService.createSalon(salonDTO, userDTO);
        SalonDTO resSalonDTO = salonMapper.mapToDTO(salon);

        return ResponseEntity.ok(resSalonDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SalonDTO> updateSalon(@RequestBody SalonDTO salonDTO, @PathVariable("id") Long id) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon = salonService.updateSalon(salonDTO, userDTO, id);

        SalonDTO resSalonDTO = salonMapper.mapToDTO(salon);

        return ResponseEntity.ok(resSalonDTO);
    }

    @GetMapping
    public ResponseEntity<List<SalonDTO>> getSalons() {
        List<Salon> salons = salonService.getAllSalons();

        List<SalonDTO> salonDTOS = salons.stream().map((salon) -> {
            SalonDTO salonDTO = salonMapper.mapToDTO(salon);
            return salonDTO;
        }).toList();

        return ResponseEntity.ok(salonDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable("id") Long id) throws Exception {
        Salon salon = salonService.getSalonById(id);

        SalonDTO resSalonDTO = salonMapper.mapToDTO(salon);

        return ResponseEntity.ok(resSalonDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(@RequestParam("city") String city) {
        List<Salon> salons = salonService.searchSalonByCity(city);

        List<SalonDTO> salonDTOS = salons.stream().map((salon) -> {
            SalonDTO salonDTO = salonMapper.mapToDTO(salon);
            return salonDTO;
        }).toList();

        return ResponseEntity.ok(salonDTOS);
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(@PathVariable("ownerId") Long salonId) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.getSalonByOwnerId(userDTO.getId());

        SalonDTO resSalonDTO = salonMapper.mapToDTO(salon);

        return ResponseEntity.ok(resSalonDTO);
    }
}
