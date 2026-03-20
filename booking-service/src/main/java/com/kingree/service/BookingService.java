package com.kingree.service;

import com.kingree.domain.BookingStatus;
import com.kingree.dto.BookingRequest;
import com.kingree.dto.SalonDTO;
import com.kingree.dto.ServiceOfferingDTO;
import com.kingree.dto.UserDTO;
import com.kingree.modal.Booking;
import com.kingree.modal.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {
    Booking createBooking(BookingRequest bookingRequest, SalonDTO salonDTO, UserDTO userDTO, Set<ServiceOfferingDTO> servicesOfferingDTO) throws Exception;

    List<Booking> getBookingByCustomer(Long customerId);

    List<Booking> getBookingBySalon(Long salonId);

    Booking getBookingById(Long id) throws Exception;

    Booking updatebooking(Long id, BookingStatus bookingStatus) throws Exception;

    List<Booking> getBookingByDate(LocalDate date, Long salonId);

    SalonReport getSalonReport(Long salonId);
}
