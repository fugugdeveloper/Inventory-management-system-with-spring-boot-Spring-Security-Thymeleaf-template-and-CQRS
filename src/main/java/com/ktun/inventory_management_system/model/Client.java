package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "client_details")
public class Client {
    @Id                     // Marks this field as the primary key
    private String client_id;       // Client's ID
    private String client_NIC;      // Client's NIC (National Identity Card) number
    private String client_name;     // Client's name
    private String client_address;  // Client's address
    private String client_contact;  // Client's contact information
    private String client_email;    // Client's email address
}
