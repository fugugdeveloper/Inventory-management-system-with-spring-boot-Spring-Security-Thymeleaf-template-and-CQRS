package com.ktun.inventory_management_system.service.command;

import com.ktun.inventory_management_system.model.Product;
import com.ktun.inventory_management_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {
    @Autowired
    ProductRepository productRepository;
    public boolean deleteProductById(String id) {
        if(productRepository.findById(id).isEmpty()) {
            return false;
        }else {
            productRepository.deleteById(id);
            return true;
        }
    }
    public void addProduct(Product product){
        productRepository.save(product);
    }
}
