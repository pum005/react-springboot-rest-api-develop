package com.example.gccoffee.controller;

import com.example.gccoffee.model.Category;
import lombok.Getter;

@Getter
public class CreateReviewRequest
{
    private final String productId;
    private final String title;
    private final String content;


    public CreateReviewRequest(String productId, String title, String content) {
        this.productId = productId;
        this.title = title;
        this.content = content;
    }

}
