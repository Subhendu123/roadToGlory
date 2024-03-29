package com.roadtoglory.splitwiseexptracker.dao;

import com.roadtoglory.splitwiseexptracker.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/*
*
*
*
        This is created by Subhendu (2023)
*
*
*
*/
@RepositoryRestResource(path = "default-expenses")
//@Repository
public interface ExpenseJPARepo extends JpaRepository<Expense, Integer>
{
    // This code is written to remove all the boilerplate code for users rest apis
}
