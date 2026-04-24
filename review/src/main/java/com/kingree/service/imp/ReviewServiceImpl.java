package com.kingree.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kingree.modal.Review;
import com.kingree.payload.dto.ReviewRequest;
import com.kingree.payload.dto.SalonDTO;
import com.kingree.payload.dto.UserDTO;
import com.kingree.repository.ReviewRepository;
import com.kingree.service.ReviewService;
import com.kingree.service.client.SalonFeignClient;
import com.kingree.service.client.UserFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final UserFeignClient userFeignClient;

    private final SalonFeignClient salonFeignClient;

    @Override
    public Review createReview(String jwt, ReviewRequest req, Long salonId) throws Exception {
        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        SalonDTO salonDTO = salonFeignClient.getSalonById(salonId).getBody();

        Review review = new Review();
        review.setReviewText(req.getReviewText());
        review.setRating(req.getRating());
        review.setSalonId(salonDTO.getId());
        review.setUserId(userDTO.getId());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsBySalonId(Long salonId) {
        return reviewRepository.findBySalonId(salonId);
    }

    @Override
    public Review updateReview(ReviewRequest req, Long id, String jwt) throws Exception {
        Review review = getReviewByid(id);

        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        if (review.getUserId().equals(userDTO.getId())) {
            throw new Exception("You don't have permission to update this review");
        }
        review.setRating(req.getRating());
        review.setReviewText(req.getReviewText());
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long id, Long userId) throws Exception {
        Review review = getReviewByid(id);
        if (review.getUserId().equals(userId)) {
            throw new Exception("You don't have permission to update this review");
        }
        reviewRepository.delete(review);
    }

    private Review getReviewByid(Long id) throws Exception {
        return reviewRepository.findById(id).orElseThrow(() -> new Exception("Review not exist"));
    }

}
