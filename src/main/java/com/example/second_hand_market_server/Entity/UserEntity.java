package com.example.second_hand_market_server.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserEntity {
    @Id Long id;
    String email;
    String password;
    Boolean is_admin;
    String user_name;
}
