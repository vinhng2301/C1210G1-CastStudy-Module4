package com.example.demo.service;

import com.example.demo.model.Warehouse;
import com.example.demo.repository.WareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareService implements IGeneric<Warehouse> {
    @Autowired
    WareRepository wareRepository;

    @Override
    public Iterable<Warehouse> findAll() {
        return wareRepository.findAll();
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return wareRepository.save(warehouse);
    }

    @Override
    public Warehouse findById(Long id) {
        return wareRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        wareRepository.deleteById(id);
    }

    public Iterable<Warehouse> findAllWareByProductId(Long id ){
        return wareRepository.findWarehousesByProductProductId(id);
    }
    //ham groupWareBysize dung den tim xem san pham co id =? con nhung size nao trong kho
    public List<String> selectSizeExist(Long id ){
        return wareRepository.selectAllSizeExistInWarehouse(id);
    }
    //Fu
    public Iterable<Warehouse> selectProductInWareBySizeAndProductId(String size, Long id ){
        return wareRepository.findWarehousesBySizeAndProductProductId(size,id);
    }

}
