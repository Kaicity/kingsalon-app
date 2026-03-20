package com.kingree.service.imp;

import com.kingree.domain.BookingStatus;
import com.kingree.dto.BookingRequest;
import com.kingree.dto.SalonDTO;
import com.kingree.dto.ServiceOfferingDTO;
import com.kingree.dto.UserDTO;
import com.kingree.modal.Booking;
import com.kingree.modal.SalonReport;
import com.kingree.repository.BookingRepository;
import com.kingree.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Autowired
    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest bookingRequest, SalonDTO salonDTO, UserDTO userDTO, Set<ServiceOfferingDTO> servicesOfferingDTO) throws Exception {
        // Calculate total duration services
        int totalDuration = servicesOfferingDTO.stream()
                .mapToInt(ServiceOfferingDTO::getDuration)
                .sum();

        LocalDateTime bookingStartTime = bookingRequest.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        Boolean isSlotAvailable = isTimeSlotAvailable(salonDTO, bookingStartTime, bookingEndTime);

        int totalPrice = servicesOfferingDTO.stream()
                .mapToInt(ServiceOfferingDTO::getPrice)
                .sum();

        Set<Long> serviceIds = servicesOfferingDTO.stream().map(ServiceOfferingDTO::getId).collect(Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setCustomerId(userDTO.getId());
        newBooking.setSalonId(salonDTO.getId());
        newBooking.setServiceIds(serviceIds);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);

        bookingRepository.save(newBooking);

        return null;
    }

    public Boolean isTimeSlotAvailable(SalonDTO salonDTO, LocalDateTime bookingStartTime, LocalDateTime bookingEndTime) throws Exception {
        List<Booking> existingBookings = getBookingBySalon(salonDTO.getId());

        LocalDateTime salonOpenTime = salonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDTO.getCloseTime().atDate(bookingStartTime.toLocalDate());

        // Check booking is available within time working of salon
        if (bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)) {
            throw new Exception("Booking time must be within salon's working house");
        }

        // Find booking is overlap time available
        for (Booking existingBooking : existingBookings) {
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();

            if (bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)) {
                throw new Exception("Slot not available, choose different time.");
            }

            if (bookingStartTime.isEqual(existingBookingStartTime) && bookingEndTime.isEqual(existingBookingEndTime)) {
                throw new Exception("Slot not available, choose different time.");
            }

        }

        return true;
    }

    @Override
    public List<Booking> getBookingByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking == null) {
            throw new Exception("Booking not found");
        }
        return existingBooking;
    }

    @Override
    public Booking updatebooking(Long id, BookingStatus bookingStatus) throws Exception {
        Booking booking = getBookingById(id);
        booking.setStatus(bookingStatus);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingByDate(LocalDate date, Long salonId) {
        List<Booking> allBookings = getBookingBySalon(salonId);

        if (date == null) {
            return allBookings;
        }

        return allBookings.stream().filter(booking -> isSameDate(booking.getStartTime(), date) ||
                        isSameDate(booking.getEndTime(), date))
                .collect(Collectors.toList());
    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().equals(date);
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        List<Booking> bookings = getBookingBySalon(salonId);

        int totalEarning = bookings.stream().mapToInt(Booking::getTotalPrice).sum();

        Integer totalBooking = bookings.size();

        List<Booking> cancelledBookings = bookings.stream()
                .filter(booking -> booking.getStatus() == BookingStatus.CANCELLED)
                .collect(Collectors.toList());

        Integer qtyCancelledBooking = cancelledBookings.size();

        Double totalRefund = cancelledBookings.stream().mapToDouble(Booking::getTotalPrice).sum();

        SalonReport salonReport = new SalonReport();
//      salonReport.setSalonName("");
        salonReport.setTotalEarning(totalEarning);
        salonReport.setTotalBooking(totalBooking);
        salonReport.setCancelledBooking(qtyCancelledBooking);
        salonReport.setTotalRefund(totalRefund);

        return salonReport;
    }
}
