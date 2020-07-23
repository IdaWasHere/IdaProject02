package com.Ida.service.Impl;

import com.Ida.dao.UserDao;
import com.Ida.entity.User;
import com.Ida.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private  String sendUsername;

    @Override
    public void register(User user) {
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public String findPassword(String username) {
        return userDao.findPassword(username);
    }

    @Override
    public void sendMail(String to, String subjet, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subjet);
        mailMessage.setText(content);
        mailMessage.setTo(to);
        mailMessage.setFrom(sendUsername);
        javaMailSender.send(mailMessage);
    }
}

