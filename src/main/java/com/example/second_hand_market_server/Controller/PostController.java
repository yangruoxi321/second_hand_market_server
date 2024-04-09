package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Entity.Post;
import com.example.second_hand_market_server.Service.PostService;
import com.example.second_hand_market_server.Service.TokenService;
import com.example.second_hand_market_server.model.PostBody;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    @Resource
    PostService postService;
    @Resource
    TokenService tokenService;

    @PostMapping("/create_post")
    public void createPost(@RequestBody PostBody post) {
        Long id = tokenService.getUserIdByToken(post.getToken());
        System.out.println(id);
        postService.createPost(id,post.getItemName(), post.getItemDescription(), post.getPrice());
    }
    @PostMapping("/deletePost")
    public void deletePost(@RequestBody PostBody post) {
        Long user_id = tokenService.getUserIdByToken(post.getToken());
//        System.out.println(user_id);
//        System.out.println(user_id);
        postService.deletePost(post.getId(),user_id);
    }
    @PostMapping("/updateItemDescription")
    public void updateItemDescription(@RequestBody PostBody post) {
        Long user_id = tokenService.getUserIdByToken(post.getToken());
        //System.out.println(user_id);
        postService.updateItemDescription(post.getId(), user_id, post.getItemDescription());
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
    @GetMapping("/search")
    public List<Post> search(@RequestBody PostBody post){
        return postService.search(post.getSearch());
    }
}
