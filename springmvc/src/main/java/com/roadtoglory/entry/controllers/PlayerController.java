package com.roadtoglory.entry.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadtoglory.entry.dtos.InstructorInformationDTO;
import com.roadtoglory.entry.entity.Agent;
import com.roadtoglory.entry.entity.Instructor;
import com.roadtoglory.entry.entity.InstructorDetail;
import com.roadtoglory.entry.entity.Player;
import com.roadtoglory.entry.repos.AgentRepository;
import com.roadtoglory.entry.repos.InstructorDetailsRepository;
import com.roadtoglory.entry.repos.InstructorRepository;
import com.roadtoglory.entry.repos.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private AgentRepository agentRepository;


    @RequestMapping(value = "/onetoone/player/save", method = RequestMethod.POST)
    public Player persistInstructorInformation(@RequestBody Player player) throws IOException {
        System.out.println("in the player persist!!");
        System.out.println(player.getWage());
        System.out.println(player.getCurrentClub().getPersonalInformation().getCity() + player.getWage());
        playerRepository.save(player);
        System.out.println("SAVING Player!!");
        playerRepository.flush();
        Player player1 = playerRepository.findAll().stream().findFirst().get();
        return player1;
    }

    @RequestMapping(value = "/onetoone/agent/save", method = RequestMethod.POST)
    public Player saveAgent(@RequestBody Agent agent, @RequestParam("playerId") Long playerId) {
        Player player = null;

        try {
            player = playerRepository.getReferenceById(playerId);
            LOG.info("************** Before adding the agent to the player *****************");
            LOG.info(player.toString());
            List<Player> players = new ArrayList<Player>();
            players.add(player);
            agent.setPlayersUnderAgent(players);
            agentRepository.save(agent);
            player = playerRepository.getReferenceById(playerId);
            LOG.info("************** After adding the agent to the player*****************");
            LOG.info(player.toString());
        }
        catch (Exception e){
            LOG.error("The exception happended during the persisting of Agent data "+agent);
            LOG.error("Error is caused by "+e.getMessage());
            e.printStackTrace();
        }
        return player;
    }

}
