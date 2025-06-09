package com.ktun.inventory_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tokens")
public class Token {
    @Id
    private Long id;
    private String token;
    private String username;
    public Token(String token, String username) {
        this.token = token;
        this.username = username;
    }

}
