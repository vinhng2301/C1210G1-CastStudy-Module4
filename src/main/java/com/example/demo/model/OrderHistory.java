package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private String color;
    private String size;
    private int quantity;
    private String timeReceived;
    private String timeOrder;
    private String status;
    private String prices;
    @ManyToOne
    @JoinColumn(name = "orders_id")
      private Orders orders;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public OrderHistory(Long id, Product product, String color, String size, int quantity, String timeReceived, String timeOrder, String status, String prices, Orders orders, AppUser appUser) {
        this.id = id;
        this.product = product;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.timeReceived = timeReceived;
        this.timeOrder = timeOrder;
        this.status = status;
        this.prices = prices;
        this.orders = orders;
        this.appUser = appUser;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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

    public String getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(String timeReceived) {
        this.timeReceived = timeReceived;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        this.timeOrder = timeOrder;
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

    public OrderHistory(Long id, Product product, String color, String size, int quantity, String timeReceived, String timeOrder, String status, String prices, AppUser appUser) {
        this.id = id;
        this.product = product;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.timeReceived = timeReceived;
        this.timeOrder = timeOrder;
        this.status = status;
        this.prices = prices;
        this.appUser = appUser;
    }
}
