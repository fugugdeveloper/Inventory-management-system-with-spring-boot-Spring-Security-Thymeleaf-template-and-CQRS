package com.ktun.inventory_management_system.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}