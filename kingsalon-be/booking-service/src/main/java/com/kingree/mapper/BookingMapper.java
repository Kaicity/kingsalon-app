package com.kingree.mapper;

import com.kingree.modal.Booking;
import com.kingree.payload.dto.BookingDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDTO mapToDto(Booking booking);
}
