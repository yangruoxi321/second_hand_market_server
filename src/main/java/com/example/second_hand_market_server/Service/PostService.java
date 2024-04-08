package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.Entity.Post;
import com.example.second_hand_market_server.Respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    public void createPost(Long user_id,String item_name, String item_description,double price){
        postRepository.createNewPost(user_id, item_name, item_description,price);
    }
    public void deletePost(Long post_id){
        postRepository.deletePostByPostId(post_id);
    }
    public void updateItemDescription(Long post_id,String itemDescription){
        postRepository.updateItemDescriptionByPostId(post_id,itemDescription);
    }
    public List<Post> getAllPost(){
        return postRepository.getAllPost();
    }
    public List<Post> getPostByUserId(Long user_id){
        return postRepository.getPostsByUserId(user_id);
    }
    public Post getPostByPostID(Long post_id){
        return postRepository.getPostByPostId(post_id);
    }
}
