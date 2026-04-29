package com.kingree.mapper;

import com.kingree.dto.BookingDTO;
import com.kingree.modal.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDTO mapToDto(Booking booking);
}
