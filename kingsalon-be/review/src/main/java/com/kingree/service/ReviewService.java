package com.kingree.service;

import java.util.List;

import com.kingree.modal.Review;
import com.kingree.payload.dto.ReviewRequest;

public interface ReviewService {

    Review createReview(String jwt, ReviewRequest req, Long salonId) throws Exception;

    List<Review> getReviewsBySalonId(Long salonId);

    Review updateReview(ReviewRequest req, Long id, String jwt) throws Exception;

    void deleteReview(Long id, Long userId) throws Exception;

}