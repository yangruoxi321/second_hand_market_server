package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.Entity.Post;
import com.example.second_hand_market_server.Response.ErrorResponse;
import com.example.second_hand_market_server.Respository.PostRepository;
import com.example.second_hand_market_server.util.MinioUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Resource
    private MinioUtil minioUtil;

    public ResponseEntity<?> createPost(Long user_id, String item_name, String item_description, double price, MultipartFile file) {
        log.info("Begin file upload for post creation: {}", file.getOriginalFilename());
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID() + extension;
            String filePath = minioUtil.upload(file.getBytes(), objectName);

            if (filePath != null) {
                postRepository.createNewPost(user_id, item_name, item_description, price, filePath);
                log.info("Post created successfully with file path: {}", filePath);
                return ResponseEntity.ok().build();
            } else {
                log.error("File upload failed during post creation. MinIO returned null path.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse("Failed to upload file during post creation."));
            }
        } catch (IOException e) {
            log.error("File upload failed during post creation due to IO exception: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Failed to process file during post creation: " + e.getMessage()));
        } catch (Exception e) {
            log.error("Unexpected error during post creation: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error occurred during post creation."));
        }
    }

    public ResponseEntity<?> testUpload(MultipartFile file) {
        log.info("Begin test file upload: {}", file.getOriginalFilename());
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID() + extension;
            String filePath = minioUtil.upload(file.getBytes(), objectName);

            if (filePath != null) {
                postRepository.testUploadImg(filePath);
                log.info("Test upload successful. File path: {}", filePath);
                return ResponseEntity.ok(filePath);
            } else {
                log.error("Test file upload failed. MinIO returned null path.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse("Test file upload failed."));
            }
        } catch (IOException e) {
            log.error("Test file upload failed due to IO exception: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Failed to process file during test upload: " + e.getMessage()));
        } catch (Exception e) {
            log.error("Unexpected error during test file upload: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Unexpected error occurred during test file upload."));
        }
    }

    // The rest of the methods remain unchanged
    public ResponseEntity<?> deletePost(Long post_id, Long user_id) {
        if (canDelete(post_id, user_id)) {
            postRepository.deletePostByPostId(post_id);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(new ErrorResponse("This user is not allowed to delete this post"), HttpStatus.FORBIDDEN);
        }
    }

    public boolean canDelete(Long post_id, Long user_id) {
        return Objects.equals(user_id, postRepository.getUserIdByPostId(post_id));
    }

    public void updateItemDescription(Long post_id, Long user_id, String itemDescription) {
        if (Objects.equals(user_id, postRepository.getUserIdByPostId(post_id))) {
            postRepository.updateItemDescriptionByPostId(post_id, itemDescription);
        }
    }

    public List<Post> getAllPost(Long user_id) {
        List<Post> ListOfPost = postRepository.getAllPost();
        for (Post pb : ListOfPost) {
            Long post_id = pb.getId();
            pb.setCanDelete(canDelete(post_id, user_id));
        }
        return ListOfPost;
    }

    public List<Post> getPostByUserId(Long user_id) {
        return postRepository.getPostsByUserId(user_id);
    }

    public Post getPostByPostID(Long post_id) {
        return postRepository.getPostByPostId(post_id);
    }

    public List<Post> search(String name) {
        String keyword = "%" + name.toLowerCase() + "%";
        return postRepository.search(keyword);
    }
}