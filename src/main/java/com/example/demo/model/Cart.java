package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numberId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private String prices;
    private String color;
    private String size;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Cart(Long numberId, AppUser appUser, Product product, int quantity, String prices, String color, String size) {
        this.numberId = numberId;
        this.appUser = appUser;
        this.product = product;
        this.quantity = quantity;
        this.prices = prices;
        this.color = color;
        this.size = size;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public Cart(AppUser appUser, Product product, int quantity, String prices, String color, String size) {
        this.appUser = appUser;
        this.product = product;
        this.quantity = quantity;
        this.prices = prices;
        this.color = color;
        this.size = size;
    }

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public Cart(AppUser appUser, Product product, int quantity, String prices) {
        this.appUser = appUser;
        this.product = product;
        this.quantity = quantity;
        this.prices = prices;
    }

    public Cart() {
    }
}
