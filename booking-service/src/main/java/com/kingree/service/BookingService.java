package com.kingree.service;

import com.kingree.domain.BookingStatus;
import com.kingree.dto.BookingRequest;
import com.kingree.dto.SalonDTO;
import com.kingree.dto.ServiceOfferingDTO;
import com.kingree.dto.UserDTO;
import com.kingree.modal.Booking;
import com.kingree.modal.SalonReport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface BookingService {
    Booking createBooking (BookingRequest bookingRequest, SalonDTO salonDTO, UserDTO userDTO, Set<ServiceOfferingDTO> servicesOfferingDTO);

    List<Booking> getBookingByCustomer(Long customerId);

    List<Booking> getBookingBySalonId(Long salonId);

    Booking getBookingById(Long id);

    Booking updatebooking(Long id, BookingStatus bookingStatus);

    List<Booking> getBookingByDate(LocalDateTime date, Long salonId);

    SalonReport getSalonReport(Long salonId);
}
