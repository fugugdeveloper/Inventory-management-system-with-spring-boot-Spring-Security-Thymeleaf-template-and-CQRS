package com.ktun.inventory_management_system.service.query;

import com.ktun.inventory_management_system.exceptions.ResourceNotFoundException;
import com.ktun.inventory_management_system.model.Product;
import com.ktun.inventory_management_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public void addProduct(Product product){
        productRepository.save(product);
    }
    public Product getProductById(String id) {
        return productRepository.findById(id) .orElseThrow(() -> new ResourceNotFoundException("Product not found with Id: " + id));
    }
}
