package com.example.second_hand_market_server.Respository;

import com.example.second_hand_market_server.Entity.Post;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository {
    @Modifying
    @Query("INSERT INTO post (user_id) VALUES (:user_id)")
    void createNewPost(@Param("user_id") Long user_id);

    @Modifying
    @Query("DELETE FROM post WHERE id = :post_id")
    void deletePostByPostId(@Param("post_id")Long post_id);

    @Query("SELECT * FROM post WHERE user_id = :user_id")
    List<Post> getPostsByUserId(@Param("user_id") Long user_id);

    @Query("SELECT * FROM post WHERE id = :post_id")
    Post getPostByPostId(@Param("id") Long post_id);

}
