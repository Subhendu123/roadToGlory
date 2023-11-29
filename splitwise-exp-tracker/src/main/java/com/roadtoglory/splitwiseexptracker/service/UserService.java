package com.roadtoglory.splitwiseexptracker.service;

import com.roadtoglory.splitwiseexptracker.models.User;
import com.roadtoglory.splitwiseexptracker.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
@Service
public class UserService
{
    @Autowired
    private UserDaoImpl userDaoImpl;

    public List<User> getUsers(){
        List<User> users = userDaoImpl.getUsers();
        System.out.println("inside the getusers method");
        for(User u : users){
            System.out.println("The user is ");
            System.out.println(u);
        }
        return users;
    }
}
