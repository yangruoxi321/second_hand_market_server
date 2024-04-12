package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Entity.Post;
import com.example.second_hand_market_server.Service.PostService;
import com.example.second_hand_market_server.Service.TokenService;
import com.example.second_hand_market_server.model.PostBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/create_post")
    public void createPost(@RequestHeader String token,
                           @RequestPart("post") String postStr,
                           @RequestPart("file") MultipartFile file) {
        Long id = tokenService.getUserIdByToken(token);
        ObjectMapper mapper = new ObjectMapper();
        try {
            PostBody post = mapper.readValue(postStr, PostBody.class);
            System.out.println(id);
            postService.createPost(id,post.getItemName(), post.getItemDescription(), post.getPrice(),file);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/testUpload")
    public void testUpload(@RequestPart("file") MultipartFile file) {
        postService.testUpload(file);
    }

    @PostMapping("/deletePost")
    public void deletePost(@RequestBody PostBody post,@RequestHeader String token) {
        Long user_id = tokenService.getUserIdByToken(token);
//        System.out.println(user_id);
//        System.out.println(user_id);
        postService.deletePost(post.getId(),user_id);
    }
    @PostMapping("/updateItemDescription")
    public void updateItemDescription(@RequestBody PostBody post,@RequestHeader String token) {
        Long user_id = tokenService.getUserIdByToken(token);
        //System.out.println(user_id);
        postService.updateItemDescription(post.getId(), user_id, post.getItemDescription());
    }
    @PostMapping("/getAllPost")
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }
    @PostMapping("/getPostByUserId")
    public List<Post> getPostByUserId(@RequestBody Post post) {
        return postService.getPostByUserId(post.getUserId());
    }
    @PostMapping("/getPostByPostID")
    public Post getPostByPostID(@RequestBody Post post) {
        return postService.getPostByPostID(post.getId());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/search")
    public List<Post> search(@RequestBody PostBody post){
        return postService.search(post.getSearch());
    }
}
