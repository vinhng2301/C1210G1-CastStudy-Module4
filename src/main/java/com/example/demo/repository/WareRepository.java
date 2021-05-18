package com.example.demo.repository;

import com.example.demo.model.Warehouse;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WareRepository extends JpaRepository<Warehouse, Long> {
    Iterable<Warehouse> findWarehousesByProductProductId(Long id);

    @Query(value = "select  v.size from Warehouse v  where v.product.productId=:id group by v.size")
    List<String> selectAllSizeExistInWarehouse(@Param("id") Long id);

    Iterable<Warehouse> findWarehousesBySizeAndProductProductId(String size, Long id);
}
