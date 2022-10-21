package com.example.gccoffee.service;

import com.example.gccoffee.model.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewService {

    Optional<Review> getReviewById(UUID id);

    List<Review> getAllReviews();

    List<Review> getReviewByProductId(UUID id);

    Review createReview(String productId, String title, String content);

}
