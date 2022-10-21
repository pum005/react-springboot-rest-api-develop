package com.example.gccoffee.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Review {

    private final UUID reviewId;
    private final UUID productId;
    private String title;
    private String content;

    private final LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Review(UUID reviewId, UUID productId, String title, String content)
    {
        this.reviewId = reviewId;
        this.productId = productId;
        this.title = title;
        this.content = content;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public Review(UUID reviewId, UUID productId, String title, String content, LocalDateTime createAt, LocalDateTime updateAt) {
        this.reviewId = reviewId;
        this.productId = productId;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    public UUID getReviewId() {
        return reviewId;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updateAt = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.updateAt = LocalDateTime.now();
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }


}
