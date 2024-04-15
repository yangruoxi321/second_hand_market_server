package com.example.second_hand_market_server.Respository;


import com.example.second_hand_market_server.Entity.User;
import com.example.second_hand_market_server.model.ProfileBody;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends CrudRepository<User, Long> {
    @Modifying
    @Query("INSERT INTO users (user_name,email,password,seller_rate,number_of_people_rated) VALUES (:userName,:email,:password,0,0)")
    void createNewUser(@Param("email") String email,@Param("userName") String userName,@Param("password") String password);

    @Query("SELECT id FROM users WHERE email = :email")
    Long findUserIDByEmail(@Param("email") String email);


    @Query("SELECT id FROM users WHERE user_name = :user_name")
    Integer findUserIDByUserName(@Param("user_name") String user_name);


    @Query("SELECT user_name FROM users WHERE id = :id")
    String findUserNameById(@Param("id") Long id);


    @Query("SELECT password FROM users WHERE id = :id")
    String getPassword(@Param("id") Long id);

    @Query("SELECT id,email,user_name,seller_rate,wallet FROM users WHERE id = :id")
    ProfileBody getProfile(@Param("id") Long id);

    @Query("SELECT seller_rate FROM users WHERE id = :id")
    Double getSellerRate(@Param("id") Long id);

    @Query("SELECT number_of_people_rated FROM users WHERE id = :id")
    Long getNumberOfPeopleRated(@Param("id") Long id);

    @Modifying
    @Query("UPDATE users SET seller_rate = :seller_rate, number_of_people_rated = :number_of_people_rated WHERE id = :id")
    void updateSellerRate(@Param("seller_rate") Double seller_rate,@Param("number_of_people_rated") Long number_of_people_rated, @Param("id") Long id );

    @Query("SELECT wallet from users where id = :id")
    Double getWallet(@Param("id") Long id);

    @Modifying
    @Query("UPDATE users SET wallet = :wallet WHERE id = :id")
    void updateWallet(@Param("wallet") Double wallet, @Param("id") Long id);
}
