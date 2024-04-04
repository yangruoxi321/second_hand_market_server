package com.example.second_hand_market_server.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import jakarta.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table("users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    String password;
    Boolean is_admin;
    String user_name;
}
