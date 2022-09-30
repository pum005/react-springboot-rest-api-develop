package com.example.gccoffee.controller;

import com.example.gccoffee.model.Category;

import java.util.Objects;

public final class CreateProductRequest {
    private final String productName;
    private final Category category;
    private final long price;
    private final String description;

    CreateProductRequest(String productName, Category category, long price, String description) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public String productName() {
        return productName;
    }

    public Category category() {
        return category;
    }

    public long price() {
        return price;
    }

    public String description() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CreateProductRequest) obj;
        return Objects.equals(this.productName, that.productName) &&
                Objects.equals(this.category, that.category) &&
                this.price == that.price &&
                Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, category, price, description);
    }

    @Override
    public String toString() {
        return "CreateProductRequest[" +
                "productName=" + productName + ", " +
                "category=" + category + ", " +
                "price=" + price + ", " +
                "description=" + description + ']';
    }

}
