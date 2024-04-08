package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Entity.Post;
import com.example.second_hand_market_server.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/create_post")
    public void createPost(@RequestBody Post post) {
        postService.createPost(post.getUserId(),post.getItemName(), post.getItemDescription(), post.getPrice());
    }
    @PostMapping("/deletePost")
    public void deletePost(@RequestBody Post postId) {
        postService.deletePost(postId.getId());
    }
    @PostMapping("/updateItemDescription")
    public void updateItemDescription(@RequestBody Post post) {
        postService.updateItemDescription(post.getId(), post.getItemDescription());
    }
    @GetMapping("/getAllPost")
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }
    @GetMapping("/getPostByUserId")
    public List<Post> getPostByUserId(@RequestBody Post post) {
        return postService.getPostByUserId(post.getUserId());
    }
    @GetMapping("/getPostByPostID")
    public Post getPostByPostID(@RequestBody Post post) {
        return postService.getPostByPostID(post.getId());
    }
}
