package com.ktun.inventory_management_system.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    @NotNull
    private String id;
    @NotNull
    private String username;
    private String fullName;
    @NotNull
    private String password;
    private String emailAddress;
    private Long phoneNumber;
    private String userImage;
    private boolean enabled;
    private  String role;

}

