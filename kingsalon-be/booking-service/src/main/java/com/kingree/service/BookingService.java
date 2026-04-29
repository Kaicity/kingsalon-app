package com.kingree.service;

import java.time.LocalDate;
import java.util.List;

import com.kingree.domain.BookingStatus;
import com.kingree.domain.PaymentMethod;
import com.kingree.dto.BookingDTO;
import com.kingree.dto.BookingRequest;
import com.kingree.dto.BookingSlotDTO;
import com.kingree.dto.PaymentLinkResponse;
import com.kingree.dto.SalonReport;
import com.kingree.dto.event.PaymentOrderEvent;

public interface BookingService {
    PaymentLinkResponse createBooking(String jwt, Long salonId, PaymentMethod paymentMethod,
            BookingRequest bookingRequest) throws Exception;

    List<BookingDTO> getBookingByCustomer(String jwt) throws Exception;

    List<BookingDTO> getBookingBySalon(String jwt) throws Exception;

    BookingDTO getBookingById(Long id) throws Exception;

    BookingDTO updatebooking(Long id, BookingStatus bookingStatus) throws Exception;

    List<BookingSlotDTO> getBookingByDate(String jwt, LocalDate date, Long salonId) throws Exception;

    SalonReport getSalonReport(String jwt) throws Exception;

    BookingDTO bookingSuccess(PaymentOrderEvent paymentOrder) throws Exception;
}
