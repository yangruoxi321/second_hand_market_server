package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.Entity.Post;
import com.example.second_hand_market_server.Respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    public void createPost(Long user_id,String item_name, String item_description,double price){
        postRepository.createNewPost(user_id, item_name, item_description,price);
    }
    public void deletePost(Long post_id,Long user_id){
        if(Objects.equals(user_id, postRepository.getUserIdByPostId(post_id))){
            postRepository.deletePostByPostId(post_id);
        }
        //System.out.println(Objects.equals(user_id, postRepository.getUserIdByPostId(post_id)));
    }
    public void updateItemDescription(Long post_id,Long user_id,String itemDescription){
        if(Objects.equals(user_id, postRepository.getUserIdByPostId(post_id))) {
            postRepository.updateItemDescriptionByPostId(post_id,itemDescription);
        }
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
    public List<Post> search (String name){
        String keyword = "%" + name.toLowerCase() + "%";
        return postRepository.search(keyword);
    }
}
