package com.kingree.repository;

import com.kingree.modal.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findBySalonId(Long salonId);

    @Query("""
                SELECT b FROM Booking b
                WHERE b.salonId = :salonId
                AND b.startTime < :end
                AND b.endTime > :start
            """)
    List<Booking> findOverlappingBookings(
            Long salonId, LocalDateTime start, LocalDateTime end);
}
