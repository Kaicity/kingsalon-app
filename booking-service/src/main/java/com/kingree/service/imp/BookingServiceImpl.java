package com.kingree.service.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kingree.domain.BookingStatus;
import com.kingree.domain.PaymentMethod;
import com.kingree.dto.BookingDTO;
import com.kingree.dto.BookingRequest;
import com.kingree.dto.BookingSlotDTO;
import com.kingree.dto.PaymentLinkResponse;
import com.kingree.dto.SalonDTO;
import com.kingree.dto.SalonReport;
import com.kingree.dto.ServiceOfferingDTO;
import com.kingree.dto.UserDTO;
import com.kingree.dto.event.PaymentOrderEvent;
import com.kingree.mapper.BookingMapper;
import com.kingree.modal.Booking;
import com.kingree.repository.BookingRepository;
import com.kingree.service.BookingService;
import com.kingree.service.client.PaymentFeignClient;
import com.kingree.service.client.SalonFeignClient;
import com.kingree.service.client.ServiceOfferingFeignClient;
import com.kingree.service.client.UserFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final SalonFeignClient salonFeignClient;
    private final UserFeignClient userFeignClient;
    private final ServiceOfferingFeignClient serviceOfferingFeignClient;
    private final PaymentFeignClient paymentFeignClient;

    private final BookingMapper bookingMapper;

    @Override
    public PaymentLinkResponse createBooking(String jwt, Long salonId, PaymentMethod paymentMethod,
            BookingRequest bookingRequest)
            throws Exception {

        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        // SalonDTO salonDTO = salonFeignClient.getSalonById(salonId).getBody();

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        salonDTO.setName("30Shine Bến Thành");
        salonDTO.setAddress("82 Nguyễn Thái Học, Phường Phạm Ngũ Lão, Quận 1");
        salonDTO.setCity("Ho Chi Minh City");
        salonDTO.setPhoneNumber("1900272703");
        salonDTO.setEmail("support@30shine.com");
        salonDTO.setOpenTime(LocalTime.parse("08:30:00"));
        salonDTO.setCloseTime(LocalTime.parse("20:30:00"));
        salonDTO.setImages(new ArrayList<>());
        salonDTO.setOwnerId(54L);

        Set<ServiceOfferingDTO> servicesOfferingDTO = serviceOfferingFeignClient
                .getServiceByIds(bookingRequest.getServiceIds()).getBody();

        // Calculate total duration services
        int totalDuration = servicesOfferingDTO.stream()
                .mapToInt(ServiceOfferingDTO::getDuration)
                .sum();

        LocalDateTime bookingStartTime = bookingRequest.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        // Find slot available
        isTimeSlotAvailable(jwt, salonDTO, bookingStartTime, bookingEndTime);

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

        // create payment in flow booking
        BookingDTO bookingDTO = bookingMapper.mapToDto(newBooking);

        System.out.println(newBooking);

        PaymentLinkResponse paymentLink = paymentFeignClient.createPaymentLink(bookingDTO, paymentMethod, jwt)
                .getBody();

        return paymentLink;
    }

    public Boolean isTimeSlotAvailable(String jwt, SalonDTO salonDTO, LocalDateTime bookingStartTime,
            LocalDateTime bookingEndTime)
            throws Exception {

        LocalDateTime salonOpenTime = salonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDTO.getCloseTime().atDate(bookingStartTime.toLocalDate());

        // Check booking is available within time working of salon
        if (bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)) {
            throw new Exception("Booking time must be within salon's working house");
        }

        List<Booking> overlaps = bookingRepository
                .findOverlappingBookings(salonDTO.getId(), bookingStartTime, bookingEndTime);

        if (!overlaps.isEmpty()) {
            throw new Exception("Slot not available");
        }

        return true;
    }

    @Override
    public List<BookingDTO> getBookingByCustomer(String jwt) throws Exception {

        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        if (userDTO == null || userDTO.getId() == null) {
            throw new Exception("User not found with jwt...");
        }

        List<Booking> bookings = bookingRepository.findByCustomerId(userDTO.getId());
        return bookings.stream().map(booking -> bookingMapper.mapToDto(booking)).toList();

    };

    @Override
    public List<BookingDTO> getBookingBySalon(String jwt) throws Exception {

        SalonDTO salonDTO = salonFeignClient.getSalonByOwnerId(jwt).getBody();
        List<Booking> bookings = bookingRepository.findBySalonId(salonDTO.getId());

        return bookings.stream().map(booking -> bookingMapper.mapToDto(booking)).toList();
    }

    @Override
    public BookingDTO getBookingById(Long id) throws Exception {

        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking == null) {
            throw new Exception("Booking not found");
        }
        return bookingMapper.mapToDto(existingBooking);
    }

    @Override
    public BookingDTO updatebooking(Long id, BookingStatus bookingStatus) throws Exception {

        Booking updateBooking = bookingRepository.findById(id).orElse(null);
        if (updateBooking == null) {
            throw new Exception("Booking not found");
        }

        updateBooking.setStatus(bookingStatus);
        bookingRepository.save(updateBooking);

        return bookingMapper.mapToDto(updateBooking);
    }

    @Override
    public List<BookingSlotDTO> getBookingByDate(String jwt, LocalDate date, Long salonId) throws Exception {

        List<BookingDTO> allBookings = getBookingBySalon(jwt);

        return allBookings.stream()
                .filter(booking -> date == null ||
                        isSameDate(booking.getStartTime(), date) ||
                        isSameDate(booking.getEndTime(), date))
                .map(booking -> {
                    BookingSlotDTO bookingSlotDTO = new BookingSlotDTO();
                    bookingSlotDTO.setStartTime(booking.getStartTime());
                    bookingSlotDTO.setEndTime(booking.getEndTime());
                    return bookingSlotDTO;
                })
                .toList();
    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().equals(date);
    }

    @Override
    public SalonReport getSalonReport(String jwt) throws Exception {

        SalonDTO salonDTO = salonFeignClient.getSalonByOwnerId(jwt).getBody();

        List<BookingDTO> bookings = getBookingBySalon(jwt);

        int totalEarning = bookings.stream().mapToInt(BookingDTO::getTotalPrice).sum();

        Integer totalBooking = bookings.size();

        List<BookingDTO> cancelledBookings = bookings.stream()
                .filter(booking -> booking.getStatus() == BookingStatus.CANCELLED)
                .collect(Collectors.toList());

        Integer qtyCancelledBooking = cancelledBookings.size();

        Double totalRefund = cancelledBookings.stream().mapToDouble(BookingDTO::getTotalPrice).sum();

        SalonReport salonReport = new SalonReport();
        salonReport.setSalonName(salonDTO.getName());
        salonReport.setSalonId(salonDTO.getId());
        salonReport.setTotalEarning(totalEarning);
        salonReport.setTotalBooking(totalBooking);
        salonReport.setCancelledBooking(qtyCancelledBooking);
        salonReport.setTotalRefund(totalRefund);

        return salonReport;
    }

    @Override
    public BookingDTO bookingSuccess(PaymentOrderEvent paymentOrder) throws Exception {

        Booking existingBooking = bookingRepository.findById(paymentOrder.getBookingId()).orElse(null);
        if (existingBooking == null) {
            throw new Exception("Booking not found");
        }

        existingBooking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(existingBooking);

        return bookingMapper.mapToDto(existingBooking);
    }

}
