package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Long phoneNumber;
    private boolean enabled;
    private MultipartFile userImage;
    private String role;

}
