package com.example.demo.controller;

import com.example.demo.model.OrderHistory;
import com.example.demo.model.Orders;
import com.example.demo.service.OrderHistoryService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class ApiAdmin {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderHistoryService orderHistoryService;

    //xem chi tiết 1 đơn hàng
    @GetMapping("/{orderId}")
    public ResponseEntity<Iterable<OrderHistory>> showOrderDetail(@PathVariable("orderId") Long id) {
        return new ResponseEntity<>(orderHistoryService.findOrderHistoryByOrderId(id), HttpStatus.OK);
    }

    //Dung de in ra order
    @GetMapping("/orders")
    public ResponseEntity<Iterable<Orders>> getAllOrders(){
        return  new ResponseEntity<>(orderService.findAll(),HttpStatus.OK);
    }

    //update trạng thái đơn hàng (giao/chưa giao....)
    @PutMapping("/{orderId}/{status}")
    public ResponseEntity<Orders> updateStatus(@PathVariable("status") String status, @PathVariable("orderId") Long id) {
        Orders orders = orderService.findById(id);
        orders.setStatus(status);

        //cập nhật cả trạng thái mới trong order-history
        Iterable<OrderHistory> list = orderHistoryService.findOrderHistoryByOrderId(id);
        for (OrderHistory o : list) {
            o.setStatus(status);
            orderHistoryService.save(o);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    //xoa san khoi muc quan li sau khi nhan hang xong
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Iterable<Orders>> deleteOrdersWhenDone(@PathVariable("orderId") Long id){
        orderService.delete(id);
        return  new ResponseEntity<>(orderService.findAll(),HttpStatus.OK);
    }
}
