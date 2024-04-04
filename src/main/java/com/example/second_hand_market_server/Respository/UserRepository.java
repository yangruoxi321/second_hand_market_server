package com.example.second_hand_market_server.Respository;


import com.example.second_hand_market_server.Entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends CrudRepository<User, Long> {
    @Modifying
    @Query("INSERT INTO users (user_name, email) VALUES (:userName, :email)")
    void createNewUser(@Param("email") String email,@Param("userName") String userName);
}



//CREATE TABLE users (
//        id INT AUTO_INCREMENT PRIMARY KEY,
//        user_name VARCHAR(255),
//email VARCHAR(255)
//);