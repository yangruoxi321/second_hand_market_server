package com.example.second_hand_market_server.Controller;

import com.example.second_hand_market_server.Service.TokenService;
import com.example.second_hand_market_server.Service.purchaseService;
import com.example.second_hand_market_server.catchException.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
