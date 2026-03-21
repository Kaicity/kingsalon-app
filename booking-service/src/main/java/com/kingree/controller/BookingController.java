package com.kingree.controller;

import com.kingree.domain.BookingStatus;
import com.kingree.dto.*;
import com.kingree.mapper.BookingMapper;
import com.kingree.modal.Booking;
import com.kingree.modal.SalonReport;
import com.kingree.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
        salonDTO.setOpenTime(LocalTime.of(8, 0));
        salonDTO.setCloseTime(LocalTime.of(20, 0));

        Set<ServiceOfferingDTO> serviceOfferingDTOSet = new HashSet<>();

        ServiceOfferingDTO serviceOfferingDTO = new ServiceOfferingDTO();
        serviceOfferingDTO.setId(1L);
        serviceOfferingDTO.setPrice(399);
        serviceOfferingDTO.setDuration(45);
        serviceOfferingDTO.setName("Health massage body for man");

        serviceOfferingDTOSet.add(serviceOfferingDTO);

        Booking booking = bookingService.createBooking(bookingRequest, salonDTO, userDTO, serviceOfferingDTOSet);

        return ResponseEntity.ok(booking);
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingBySalon() {
        List<Booking> bookings = bookingService.getBookingBySalon(1L);

        return ResponseEntity.ok(getBookingDTOS(bookings));
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingByCustomer() {
        List<Booking> bookings = bookingService.getBookingByCustomer(1L);

        return ResponseEntity.ok(getBookingDTOS(bookings));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable("id") Long id) throws Exception {
        Booking booking = bookingService.getBookingById(id);

        return ResponseEntity.ok(bookingMapper.mapToDto(booking));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookingDTO> getBookingStatus(
            @PathVariable("id") Long id,
            @RequestParam BookingStatus bookingStatus) throws Exception {
        Booking booking = bookingService.updatebooking(id, bookingStatus);

        return ResponseEntity.ok(bookingMapper.mapToDto(booking));
    }

    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookingsByDate(
            @PathVariable("salonId") Long salonId,
            @RequestParam(required = false) LocalDate date) throws Exception {

        List<Booking> bookings = bookingService.getBookingByDate(date, salonId);

        List<BookingSlotDTO> bookingSlotDTOS = bookings.stream().map(booking -> {
            BookingSlotDTO bookingSlotDTO = new BookingSlotDTO();
            bookingSlotDTO.setStartTime(booking.getStartTime());
            bookingSlotDTO.setEndTime(bookingSlotDTO.getEndTime());
            return bookingSlotDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(bookingSlotDTOS);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport() {
        SalonReport salonReport = bookingService.getSalonReport(1L);

        return ResponseEntity.ok(salonReport);
    }

    private Set<BookingDTO> getBookingDTOS(List<Booking> bookings) {
        if (bookings == null) return Collections.emptySet();

        return bookings.stream()
                .map(bookingMapper::mapToDto)
                .collect(Collectors.toSet());
    }
}
