package com.example.second_hand_market_server.Respository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository {
    @Modifying
    @Query("INSERT INTO users (userName, email) VALUES (:userName, :email)")
    void createNewUser(@Param("userName") String userName, @Param("email") String email);
}



//CREATE TABLE users (
//        id INT AUTO_INCREMENT PRIMARY KEY,
//        user_name VARCHAR(255),
//email VARCHAR(255)
//);