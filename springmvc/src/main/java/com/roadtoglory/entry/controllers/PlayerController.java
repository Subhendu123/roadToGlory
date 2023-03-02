package com.roadtoglory.entry.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roadtoglory.entry.dtos.InstructorInformationDTO;
import com.roadtoglory.entry.entity.Instructor;
import com.roadtoglory.entry.entity.InstructorDetail;
import com.roadtoglory.entry.entity.Player;
import com.roadtoglory.entry.repos.InstructorDetailsRepository;
import com.roadtoglory.entry.repos.InstructorRepository;
import com.roadtoglory.entry.repos.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;


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

}
