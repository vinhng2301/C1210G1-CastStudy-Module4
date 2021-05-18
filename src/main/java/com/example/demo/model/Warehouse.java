package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long warehouseId;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    private String color;
    private String size;
    private int quantity;

    public Warehouse() {
    }

    public Warehouse(Long warehouseId, Product product, String color, String size, int quantity) {
        this.warehouseId = warehouseId;
        this.product = product;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
