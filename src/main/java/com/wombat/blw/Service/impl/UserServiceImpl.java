package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.User;
import com.wombat.blw.Form.UserSignInForm;
import com.wombat.blw.Form.UserSignUpForm;
import com.wombat.blw.Mapper.UserMapper;
import com.wombat.blw.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private WebSocket webSocket;
//
//    @Override
//    public void create(User user) {
//        userMapper.insert(user);
//
//        //send WebSocket message
//        webSocket.sendMessage("New User!");
//    }
//
//    @Override
//    public void delete(Integer userId) {
//
//    }
//
//    @Override
//    public List<User> getAll() {
//        return userMapper.getAll();
//    }

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getOne(UserSignInForm userSignInForm) {
        //TODO
        return null;
    }

    @Override
    public void create(UserSignUpForm userSignUpForm) {
        //TODO
    }
}
