package com.example.second_hand_market_server.Respository;

import com.example.second_hand_market_server.Entity.Post;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    @Modifying
    @Query("INSERT INTO post (user_id,item_name,item_description,price,img_url) VALUES (:user_id,:item_name,:item_description,:price,:img_url)")
    void createNewPost( Long user_id,String item_name,String item_description, Double price,String img_url);

    @Modifying
    @Query("DELETE FROM post WHERE id = :post_id")
    void deletePostByPostId(Long post_id);

    @Query("SELECT * FROM post WHERE user_id = :user_id")
    List<Post> getPostsByUserId(Long user_id);

    @Query("SELECT * FROM post")
    List<Post> getAllPost();

    @Query("SELECT * FROM post WHERE id = :id")
    Post getPostByPostId( Long id);

    @Modifying
    @Query("UPDATE post SET item_description = :item_description WHERE id = :post_id")
    void updateItemDescriptionByPostId(Long post_id, String item_description);

    @Query("SELECT user_id FROM post WHERE id = :post_id")
    Long getUserIdByPostId(Long post_id);

    @Query("SELECT * FROM Post  WHERE item_description ILIKE :name OR item_name ILIKE :name")
    List<Post> search(String name);

    @Modifying
    @Query("INSERT INTO post (img_url) VALUES (:img_url)")
    void testUploadImg(String img_url);

}
