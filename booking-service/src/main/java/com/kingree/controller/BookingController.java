package com.kingree.controller;

import com.kingree.dto.*;
import com.kingree.mapper.BookingMapper;
import com.kingree.modal.Booking;
import com.kingree.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    @Autowired
    private final BookingMapper bookingMapper;

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long salonId,
            @RequestBody BookingRequest bookingRequest
    ) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salonId);

        Set<ServiceOfferingDTO> serviceOfferingDTOSet = new HashSet<>();
        ServiceOfferingDTO serviceOfferingDTO = new ServiceOfferingDTO();
        serviceOfferingDTO.setId(1L);
        serviceOfferingDTO.setPrice(399);
        serviceOfferingDTO.setDuration(45);
        serviceOfferingDTO.setName("Health massage body for man");

        Booking booking = bookingService.createBooking(bookingRequest, salonDTO, userDTO, serviceOfferingDTOSet);

        return ResponseEntity.ok(booking);
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingByCustomer() {
        List<Booking> bookings = bookingService.getBookingByCustomer(1L);

        return ResponseEntity.ok(getBookingDTOS(bookings));
    }

    private Set<BookingDTO> getBookingDTOS(List<Booking> bookings) {
        if (bookings == null) return Collections.emptySet();

        return bookings.stream()
                .map(bookingMapper::mapToDto)
                .collect(Collectors.toSet());
    }
}
