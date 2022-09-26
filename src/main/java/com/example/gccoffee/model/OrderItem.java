package com.example.gccoffee.model;

import java.util.Objects;
import java.util.UUID;
public record OrderItem(UUID productId, Category category, long price, int quantity) {
}