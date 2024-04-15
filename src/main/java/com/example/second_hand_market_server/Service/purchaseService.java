package com.example.second_hand_market_server.Service;

import com.example.second_hand_market_server.Entity.Post;
import com.example.second_hand_market_server.Respository.PostRepository;
import com.example.second_hand_market_server.Respository.UserRepository;
import com.example.second_hand_market_server.catchException.InsufficientBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

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
        postRepository.setIsPurchasedStatusToTrue(buyer_id,post_id);
    }
    public List<Post> purchasedItem(Long user_id){
        return postRepository.purchasedItem(user_id);
    }
    public void rateSeller(Long post_id,Double rate){
        Long seller_id = postRepository.getUserIdByPostId(post_id);
        //System.out.println(seller_id);
        Double rateInDb =  userRepository.getSellerRate(seller_id);
        Long numberOfPeopleRated  = userRepository.getNumberOfPeopleRated(seller_id);
        Double totalRate = rateInDb * numberOfPeopleRated + rate;
        Long newNumberOfPeopleRated = numberOfPeopleRated + 1;
        Double newRate = (totalRate / newNumberOfPeopleRated);
        Double roundNewRate = Math.round(newRate * 100.0) / 100.0;
        userRepository.updateSellerRate(roundNewRate,newNumberOfPeopleRated, seller_id);

    }
}
