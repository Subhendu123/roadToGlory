package com.roadtoglory.entry.repos;


import com.roadtoglory.entry.entity.Agent;
import com.roadtoglory.entry.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

}
