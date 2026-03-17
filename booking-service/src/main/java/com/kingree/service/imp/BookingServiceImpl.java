package com.kingree.service.imp;

import com.kingree.domain.BookingStatus;
import com.kingree.dto.BookingRequest;
import com.kingree.dto.SalonDTO;
import com.kingree.dto.ServiceOfferingDTO;
import com.kingree.dto.UserDTO;
import com.kingree.modal.Booking;
import com.kingree.modal.SalonReport;
import com.kingree.service.BookingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class BookingServiceImpl implements BookingService {
    @Override
    public Booking createBooking(BookingRequest bookingRequest, SalonDTO salonDTO, UserDTO userDTO, Set<ServiceOfferingDTO> servicesOfferingDTO) {
        return null;
    }

    @Override
    public List<Booking> getBookingByCustomer(Long customerId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingBySalonId(Long salonId) {
        return List.of();
    }

    @Override
    public Booking getBookingById(Long id) {
        return null;
    }

    @Override
    public Booking updatebooking(Long id, BookingStatus bookingStatus) {
        return null;
    }

    @Override
    public List<Booking> getBookingByDate(LocalDateTime date, Long salonId) {
        return List.of();
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        return null;
    }
}
