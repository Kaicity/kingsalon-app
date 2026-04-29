package com.kingree.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kingree.domain.BookingStatus;
import com.kingree.domain.PaymentMethod;
import com.kingree.dto.BookingDTO;
import com.kingree.dto.BookingRequest;
import com.kingree.dto.BookingSlotDTO;
import com.kingree.dto.PaymentLinkResponse;
import com.kingree.dto.SalonReport;
import com.kingree.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<PaymentLinkResponse> createBooking(
            @RequestParam Long salonId,
            @RequestParam PaymentMethod paymentMethod,
            @RequestBody BookingRequest bookingRequest,
            @RequestHeader("Authorization") String jwt) throws Exception {

        PaymentLinkResponse res = bookingService.createBooking(jwt, salonId, paymentMethod, bookingRequest);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/salon")
    public ResponseEntity<List<BookingDTO>> getBookingBySalon(@RequestHeader("Authorization") String jwt)
            throws Exception {

        return ResponseEntity.ok(bookingService.getBookingBySalon(jwt));
    }

    @GetMapping("/customer")
    public ResponseEntity<List<BookingDTO>> getBookingByCustomer(@RequestHeader("Authorization") String jwt)
            throws Exception {

        return ResponseEntity.ok(bookingService.getBookingByCustomer(jwt));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookingDTO> getBookingStatus(
            @PathVariable Long id,
            @RequestParam BookingStatus bookingStatus) throws Exception {

        return ResponseEntity.ok(bookingService.updatebooking(id, bookingStatus));
    }

    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookingsByDate(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long salonId,
            @RequestParam(required = false) LocalDate date) throws Exception {

        return ResponseEntity.ok(bookingService.getBookingByDate(jwt, date, salonId));
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport(@RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(bookingService.getSalonReport(jwt));
    }

}
