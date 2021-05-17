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

    public AppUser getAppUser() {
        return appUser;
    }

    public Cart(Long numberId, AppUser appUser, Product product, int quantity, String prices) {
        this.numberId = numberId;
        this.appUser = appUser;
        this.product = product;
        this.quantity = quantity;
        this.prices = prices;
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
