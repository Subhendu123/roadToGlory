package com.roadtoglory.entry.repos;


import com.roadtoglory.entry.entity.InstructorDetail;
import com.roadtoglory.entry.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
