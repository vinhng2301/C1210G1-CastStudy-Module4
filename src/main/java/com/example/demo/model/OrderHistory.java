package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String prices;
    private Date timeReceived;
    private Date  timeOrder;
    private String status;

    public OrderHistory() {
    }
    @OneToOne
    @JoinColumn(name="user_id")
    private AppUser appUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public Date getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(Date timeReceived) {
        this.timeReceived = timeReceived;
    }

    public Date getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(Date timeOrder) {
        this.timeOrder = timeOrder;
    }

    public OrderHistory(Long id, Product product, String prices, Date timeReceived, Date timeOrder, String status, AppUser appUser) {
        this.id = id;
        this.product = product;
        this.prices = prices;
        this.timeReceived = timeReceived;
        this.timeOrder = timeOrder;
        this.status = status;
        this.appUser = appUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
