package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.User;
import com.wombat.blw.Form.UserSignInForm;
import com.wombat.blw.Form.UserSignUpForm;
import com.wombat.blw.Mapper.UserMapper;
import com.wombat.blw.Service.UserService;
import org.springframework.beans.BeanUtils;
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
        User user = userMapper.findUserByUsername(userSignInForm.getUsername());
        if (user == null) {
            //TODO Username does not exist
            return null;
        }
        if (!user.getPassword().equals(userSignInForm.getPassword())) {
            //TODO Incorrect password
            return null;
        } else {
            return user;
        }
    }

    @Override
    public User create(UserSignUpForm userSignUpForm) {
        if (userMapper.findUserByUsername(userSignUpForm.getUsername()) != null) {
            //TODO Username already exists

        }
        User user = new User();
        BeanUtils.copyProperties(userSignUpForm, user);
        userMapper.create(user);
        return userMapper.findUserByUserId(user.getUserId());
    }
}
