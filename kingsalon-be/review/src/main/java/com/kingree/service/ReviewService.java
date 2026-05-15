package com.kingree.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kingree.modal.Review;
import com.kingree.payload.dto.ReviewRequest;

public interface ReviewService {

    Review createReview(String jwt, ReviewRequest req, Long salonId) throws Exception;

    Page<Review> getReviewsBySalonId(Long salonId, Pageable pageable);

    Review updateReview(ReviewRequest req, Long id, String jwt) throws Exception;

    void deleteReview(Long id, Long userId) throws Exception;

}