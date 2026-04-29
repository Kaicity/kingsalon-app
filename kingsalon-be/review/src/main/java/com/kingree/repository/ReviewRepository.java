package com.kingree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kingree.modal.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findBySalonId(Long salonId);
}
