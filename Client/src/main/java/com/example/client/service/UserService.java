package com.example.client.service;

import com.example.client.dao.UserDao;
import com.example.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public int Register(String userId,String password){
        return userDao.RegisterUser(userId,password);
    }
    public boolean Verification(String userId){
        return userDao.VerificationIfAvailable(userId) != null && userDao.VerificationIfAvailable(userId) != "";
    }
    public List<User> findAll(){
        return userDao.findAll();
    }
    public boolean Login(String userId,String password){
        return userDao.login(userId,password) != null;
    }
    public List<User> findByName(String username){
        return userDao.findByName(username);
    }
    public List<User> findFriend(String userId){
        return userDao.findFriend(userId);
    }
    public int AddFriend(String userId,String friendId){
        return userDao.insertFriend(userId,friendId);
    }
    public User GetUser(String userId){
        return userDao.getUserByUserId(userId);
    }
}
