package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.Respository.PostRepository;
import com.example.second_hand_market_server.Respository.UserRepository;
import com.example.second_hand_market_server.catchException.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class purchaseService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;
    @Transactional
    public void purchaseItem(Long post_id,Long buyer_id) throws InsufficientBalanceException {
        Double price = postRepository.getPrice(post_id);

        Long seller_id = postRepository.getUserIdByPostId(post_id);

        Double sellerMoney = userRepository.getWallet(seller_id);

        Double buyerMoney = userRepository.getWallet(buyer_id);

        Double newSellerMoney = sellerMoney + price;
        Double newBuyerMoney = buyerMoney - price;

        if (newBuyerMoney < 0) {
            throw new InsufficientBalanceException("余额不足！");
        }

        userRepository.updateWallet(newSellerMoney, seller_id);
        userRepository.updateWallet(newBuyerMoney, buyer_id);
    }

}
