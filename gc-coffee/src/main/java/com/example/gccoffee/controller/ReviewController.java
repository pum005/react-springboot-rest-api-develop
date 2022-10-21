package com.example.gccoffee.controller;

import com.example.gccoffee.model.Review;
import com.example.gccoffee.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService)
    {
        this.reviewService = reviewService;
    }

    @GetMapping("/review/{id}")
    public String reviewPage(Model model, @PathVariable String id)
    {
        List<Review> reviewByProductId = reviewService.getReviewByProductId(UUID.fromString(id));
        model.addAttribute("reviewList",reviewByProductId);
        model.addAttribute("productId" , id);
        return "review/reviewlist";
    }

    @GetMapping("/review/save/{id}")
    public String reviewsPage(Model model, @PathVariable String id)
    {
        model.addAttribute("productId", id);
        return "review/saveForm";
    }

    @PostMapping("/reviews")
    public String newReview(@ModelAttribute CreateReviewRequest createReviewRequest)
    {
        log.info(" {}", createReviewRequest.getProductId());
        reviewService.createReview(
                createReviewRequest.getProductId(),
                createReviewRequest.getTitle(),
                createReviewRequest.getContent()
        );
        return "redirect:/review/" + createReviewRequest.getProductId();
    }

}
