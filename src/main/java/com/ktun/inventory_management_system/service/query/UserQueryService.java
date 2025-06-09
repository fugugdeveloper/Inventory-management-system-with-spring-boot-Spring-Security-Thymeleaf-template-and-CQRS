package com.ktun.inventory_management_system.service.query;

import com.ktun.inventory_management_system.model.User;
import com.ktun.inventory_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryService {
    @Autowired
    private UserRepository userRepository;
    public User getByUsername(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
