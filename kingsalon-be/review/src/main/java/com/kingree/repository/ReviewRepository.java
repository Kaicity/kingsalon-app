package com.kingree.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kingree.modal.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findBySalonId(Long salonId, Pageable pageable);
}
