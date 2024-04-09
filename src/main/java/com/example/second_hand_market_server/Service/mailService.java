package com.example.second_hand_market_server.Service;

import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class mailService {
    @Resource
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

