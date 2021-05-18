package com.example.demo.repository;

import com.example.demo.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareRepository extends JpaRepository<Warehouse, Long> {
    Iterable<Warehouse> findWarehousesByProductProductId(Long id);
}
