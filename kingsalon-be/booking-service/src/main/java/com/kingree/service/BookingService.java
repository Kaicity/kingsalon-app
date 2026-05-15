package com.kingree.service;

import java.time.LocalDate;
import java.util.List;

import com.kingree.domain.BookingStatus;
import com.kingree.domain.PaymentMethod;
import com.kingree.payload.dto.BookingDTO;
import com.kingree.payload.dto.BookingRequest;
import com.kingree.payload.dto.BookingSlotDTO;
import com.kingree.payload.dto.PaymentLinkResponse;
import com.kingree.payload.dto.SalonReport;
import com.kingree.payload.dto.event.PaymentOrderEvent;

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
