package com.example.gccoffee.repository;

import com.example.gccoffee.model.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository {
    List<Review> findAll();

    Review insert(Review review);

    Review update(Review review);

    Optional<Review> findById(UUID reviewId);

    List<Review> findByProductId(UUID productId);

    void deleteAll();
}
