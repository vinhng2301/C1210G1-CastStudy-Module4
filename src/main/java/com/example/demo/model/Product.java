package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String category;
    private String productName;
    private String size;
    private boolean gender;
    private String prices;
    private String description;

    public Product() {
    }

    public Product(Long productId, String category, String productName, String size, boolean gender, String prices, String description) {
        this.productId = productId;
        this.category = category;
        this.productName = productName;
        this.size = size;
        this.gender = gender;
        this.prices = prices;
        this.description = description;
    }

    public Long getProductId(){
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
