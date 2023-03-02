package com.roadtoglory.entry.repos;


import com.roadtoglory.entry.entity.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorDetailsRepository extends JpaRepository<InstructorDetail, Integer> {

}
