package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerForm {
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerCountry;
    private String customerAddress;
    private MultipartFile customerAvatar;
    private String customerDescription;
    private String customerStatus;
}
