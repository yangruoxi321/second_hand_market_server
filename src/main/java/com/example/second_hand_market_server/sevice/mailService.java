package com.example.second_hand_market_server.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class mailService {
    @Autowired
    JavaMailSenderImpl mailSender;
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yangruoxi321@gmail.com"); // 发件人邮箱
        message.setTo(to); // 收件人邮箱
        message.setSubject(subject); // 邮件主题
        message.setText(text); // 邮件内容

        mailSender.send(message);
    }
}

