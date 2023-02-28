package com.example.client.dao;

import com.example.client.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    int RegisterUser(@Param("userId") String userid,@Param("password") String password);
    String VerificationIfAvailable(String userId);
    List<User> findAll();
    User login(@Param("userId") String userid,@Param("password") String password);
    List<User> findByName(@Param("username") String username);
    List<User> findFriend(@Param("userId") String userid);
    int insertFriend(@Param("userId") String userid,@Param("friendId") String friendId);
    User getUserByUserId(@Param("userId") String userid);
}
