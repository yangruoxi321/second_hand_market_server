package com.example.second_hand_market_server.Respository;

import com.example.second_hand_market_server.Entity.Post;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    @Modifying
    @Query("INSERT INTO post (user_id,item_name,item_description,price) VALUES (:user_id,:item_name,:item_description,:price)")
    void createNewPost(@Param("user_id") Long user_id,String item_name,String item_description, Double price);

    @Modifying
    @Query("DELETE FROM post WHERE id = :post_id")
    void deletePostByPostId(@Param("post_id")Long post_id);

    @Query("SELECT * FROM post WHERE user_id = :user_id")
    List<Post> getPostsByUserId(@Param("user_id") Long user_id);

    @Query("SELECT * FROM post")
    List<Post> getAllPost();

    @Query("SELECT * FROM post WHERE id = :id")
    Post getPostByPostId(@Param("id") Long id);

    @Modifying
    @Query("UPDATE post SET item_description = :item_description WHERE id = :post_id")
    void updateItemDescriptionByPostId(@Param("post_id") Long post_id, @Param("item_description") String item_description);




}
