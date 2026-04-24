package com.kingree.mapper;

import com.kingree.modal.Salon;
import com.kingree.payload.dto.SalonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalonMapper {
    SalonDTO mapToDTO(Salon salon);
}

