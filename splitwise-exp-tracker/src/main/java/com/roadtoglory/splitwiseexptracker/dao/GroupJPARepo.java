package com.roadtoglory.splitwiseexptracker.dao;

import com.roadtoglory.splitwiseexptracker.models.Group;
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
@RepositoryRestResource(path = "groups")
public interface GroupJPARepo extends JpaRepository<Group, Integer>
{
        // This code is written to remove all the boiler plate code for users rest apis

}

