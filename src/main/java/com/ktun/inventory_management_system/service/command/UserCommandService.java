package com.ktun.inventory_management_system.service.command;

import com.ktun.inventory_management_system.model.SaveResponse;
import com.ktun.inventory_management_system.model.User;
import com.ktun.inventory_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {
    @Autowired
    private UserRepository userRepository;
    public SaveResponse addUser(User user) {
        try {
            User addedUser = userRepository.save(user);
            if (addedUser.getId() != null) {
                return new SaveResponse(true, "User added successfully.");
            } else {
                return new SaveResponse(false, "User adding failed.");
            }
        } catch (Exception e) {
            return new SaveResponse(false, "Error occurred: " + e.getMessage());
        }
    }
}
