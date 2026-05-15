package com.kingree.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kingree.modal.Salon;

public interface SalonRepository extends JpaRepository<Salon, Long> {
    Salon findByOwnerId(Long ownerId);

    @Query("select s from Salon s " +
            "where lower(s.city) like lower(concat('%', :keyword, '%')) " +
            "or lower(s.name) like lower(concat('%', :keyword, '%')) " +
            "or lower(s.address) like lower(concat('%', :keyword, '%'))")
    Page<Salon> searchSalons(@Param("keyword") String keyword, Pageable pageable);
}
