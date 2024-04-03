package com.example.second_hand_market_server.respository;

import com.example.second_hand_market_server.Entity.UserEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;

public interface UserRepository {
    UserEntity userentity = new UserEntity();
    long id = userentity.getId();
    String user_name = userentity.getUser_name();

    @Modifying
    @Query("UPDATE customers SET first_name = :firstName, last_name = :lastName WHERE id = :id")
    void updateNameBy(long id, String user_name);
}
