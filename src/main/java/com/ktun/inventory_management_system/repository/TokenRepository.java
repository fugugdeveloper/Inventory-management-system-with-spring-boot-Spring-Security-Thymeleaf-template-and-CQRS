package com.ktun.inventory_management_system.repository;

import com.ktun.inventory_management_system.model.Token;
import com.ktun.inventory_management_system.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TokenRepository extends MongoRepository<Token, Long> {
    Optional<Token> findByUsername(String username);
}
