package com.example.gccoffee.service;

import com.example.gccoffee.model.Review;
import com.example.gccoffee.repository.ProductRepository;
import com.example.gccoffee.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultReviewService implements ReviewService{

    private final ReviewRepository reviewRepository;


    public DefaultReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getReviewByProductId(UUID id)
    {
        return reviewRepository.findByProductId(id);
    }

    @Override
    public Optional<Review> getReviewById(UUID id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review createReview(String productId, String title, String content) {
        Review review = new Review(UUID.randomUUID(), UUID.fromString(productId), title, content, LocalDateTime.now(), LocalDateTime.now());
        return reviewRepository.insert(review);
    }
}
