package com.ktun.inventory_management_system.repository;

import com.ktun.inventory_management_system.model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String > {
}
