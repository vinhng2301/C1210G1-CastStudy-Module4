package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity
@Table
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String status;
    private Date orderDate;
    private String information;//để lưu tổng hợp 1 phát cả tên -sdt -địa chỉ
    //trong trường hợp đặt hàng hộ người khác
    private String total;
    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id")
    private AppUser appUser;
    @OneToMany
    @JsonIgnore
    Set<OrderHistory> list;

    public Orders(Long orderId, String status, Date orderDate, String information, String total, AppUser appUser, Set<OrderHistory> list) {
        this.orderId = orderId;
        this.status = status;
        this.orderDate = orderDate;
        this.information = information;
        this.total = total;
        this.appUser = appUser;
        this.list = list;
    }

    public Set<OrderHistory> getList() {
        return list;
    }

    public void setList(Set<OrderHistory> list) {
        this.list = list;
    }

    public Orders(Long orderId, String status, Date orderDate, String information, String total, AppUser appUser) {
        this.orderId = orderId;
        this.status = status;
        this.orderDate = orderDate;
        this.information = information;
        this.total = total;
        this.appUser = appUser;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }


    public Orders() {
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Orders(Long orderId, String status, Date orderDate, String total, AppUser appUser) {
        this.orderId = orderId;
        this.status = status;
        this.orderDate = orderDate;
        this.total = total;
        this.appUser = appUser;
    }


    public Orders(Long orderId, String status, Date orderDate, String total) {
        this.orderId = orderId;
        this.status = status;
        this.orderDate = orderDate;
        this.total = total;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
