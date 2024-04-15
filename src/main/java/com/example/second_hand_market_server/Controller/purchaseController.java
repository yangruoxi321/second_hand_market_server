package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Entity.Post;
import com.example.second_hand_market_server.Service.TokenService;
import com.example.second_hand_market_server.Service.purchaseService;
import com.example.second_hand_market_server.catchException.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class purchaseController {
    @Autowired
    purchaseService purchaseService;

    @Autowired
    TokenService tokenService;

    @PostMapping("/purchase")
    public ResponseEntity<String> handlePurchase(@RequestHeader String token,@RequestHeader Long post_id) {
        Long buyer_id = tokenService.getUserIdByToken(token);
        try {
            purchaseService.purchaseItem(post_id, buyer_id);
            return ResponseEntity.ok("Purchase successful");
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance");
        }
    }
    @PostMapping("/purchasedItem")
    public List<Post> purchasedItem(@RequestHeader String token){
        Long userId = tokenService.getUserIdByToken(token);
        return purchaseService.purchasedItem(userId);
    }
    @PostMapping("/rateSeller")
    public void rateSeller(@RequestHeader Long postId,@RequestHeader Double rate){
        purchaseService.rateSeller(postId,rate);
    }

}
