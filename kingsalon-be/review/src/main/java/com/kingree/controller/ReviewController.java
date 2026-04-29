package com.kingree.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingree.modal.Review;
import com.kingree.payload.dto.ReviewRequest;
import com.kingree.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/salon/{salonId}")
    public ResponseEntity<Review> createReview(
            @RequestHeader("Authorization") String jwt,
            @RequestBody ReviewRequest reviewRequest,
            @PathVariable Long salonId) throws Exception {

        return ResponseEntity.ok(reviewService.createReview(jwt, reviewRequest, salonId));
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<Review>> getAllReviewsBySalon(@PathVariable Long salonId) {

        return ResponseEntity.ok(reviewService.getReviewsBySalonId(salonId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewRequest reviewRequest,
            @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(reviewService.updateReview(reviewRequest, id, jwt));
    }

    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable Long id,
            @PathVariable Long userId,
            @RequestBody ReviewRequest reviewRequest) throws Exception {

        reviewService.deleteReview(id, userId);

        return ResponseEntity.ok("Review has been deleted with id " + id);
    }

}
