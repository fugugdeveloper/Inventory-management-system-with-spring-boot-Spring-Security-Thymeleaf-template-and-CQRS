package com.ktun.inventory_management_system.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer {
    @Id
    private String customerId;

    @NotBlank(message = "Customer name is required")
    private String customerName;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,12}$", message = "Invalid phone number")
    private String customerPhone;
    @Email(message = "Invalid email format")
    private String customerEmail;
    @NotBlank(message = "Address is required")
    private String customerAddress;
    @NotBlank(message = "Country is required")
    private String customerCountry;
    private String customerDescription;
    private String customerAvatar;
    private String status;
    private String createdBy;
    private String createdDate;
}
