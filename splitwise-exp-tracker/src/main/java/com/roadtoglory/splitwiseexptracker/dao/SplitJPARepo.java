package com.roadtoglory.splitwiseexptracker.dao;

import com.roadtoglory.splitwiseexptracker.models.User;
import com.roadtoglory.splitwiseexptracker.models.UserExpense;
import com.roadtoglory.splitwiseexptracker.models.UserExpenseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
@RepositoryRestResource(path = "default-splits", exported = false)
public interface SplitJPARepo extends JpaRepository<UserExpense, Integer>
{

}
