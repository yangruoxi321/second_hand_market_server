package com.example.second_hand_market_server.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Data
public class userEntity {
    @Id Long id;
    String email;
    String password;
    Boolean is_admin;
    String user_name;
}
