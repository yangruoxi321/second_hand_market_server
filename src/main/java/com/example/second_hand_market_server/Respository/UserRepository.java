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
    @Query("INSERT INTO users (user_name,email,password) VALUES (:userName,:email,:password)")
    void createNewUser(@Param("email") String email,@Param("userName") String userName,@Param("password") String password);

    @Query("SELECT id FROM users WHERE email = :email")
    Long findUserIDByEmail(@Param("email") String email);


    @Query("SELECT id FROM users WHERE user_name = :user_name")
    Integer findUserIDByUserName(@Param("user_name") String user_name);


    @Query("SELECT user_name FROM users WHERE id = :id")
    String findUserNameById(@Param("id") Long id);


    @Query("SELECT password FROM users WHERE id = :id")
    String getPassword(@Param("id") Long id);

    @Query("SELECT * FROM users WHERE id = :id")
    User getUserById(@Param("id") Long id);

}
