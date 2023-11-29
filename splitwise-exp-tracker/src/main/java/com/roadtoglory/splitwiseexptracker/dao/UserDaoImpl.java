package com.roadtoglory.splitwiseexptracker.dao;

import com.roadtoglory.splitwiseexptracker.models.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
@Repository
public class UserDaoImpl implements UserDao
{
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<User> getUsers ()
    {
        return (List<User>) entityManager.createQuery("from User").getResultList();
    }
}
