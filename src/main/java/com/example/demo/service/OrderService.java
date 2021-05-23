package com.example.demo.service;

import com.example.demo.model.Orders;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IGeneric<Orders> {
    @Autowired
    OrderRepository orderRepository;

    public Page<Orders> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Iterable<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public Orders findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public Iterable<Orders> searchOrderByKey(String key, String key2) {
        return orderRepository.findAllByAppUserNameContainingOrAppUser_PhoneContaining(key, key2);
    }

    public Iterable<Orders> findAllByStatus(String status) {
        return orderRepository.findAllByStatus(status);

    }

    public Optional<Orders> findOrderByStatus(String status) {
        return orderRepository.findOrdersByStatus(status);
    }

    public Page<Orders> findOrdersByStatusNotDone(String status, Pageable pageable) {
        return orderRepository.findAllByStatusNot(status, pageable);
    }

    public boolean checkOrdersStatusByUserId(Long id) {
        Optional<Orders> orders =  orderRepository.findOrdersByAppUserUserId(id);
        if(!orders.isPresent() || !orders.get().getStatus().equalsIgnoreCase("done")){
            return false;
        }
        return true;
    }

}
