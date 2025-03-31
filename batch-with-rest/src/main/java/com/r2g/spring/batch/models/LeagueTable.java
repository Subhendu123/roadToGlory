package com.r2g.spring.batch.models;

/*
*
*
   This is created by Subhendu (2025) for the project: batch-with-rest
        
   @Package name com.r2g.spring.batch.models
   @Author Subhendu
   @Date 31-Jan-2025 07:16
*
*
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueTable
{

    @JsonProperty("overall_league_position")
    private int position;

    @JsonProperty("team_name")
    private String teamname;

    @JsonProperty("overall_league_PTS")
    private int points;
    @JsonProperty("overall_league_payed")
    private int matchesPlayed;

    @JsonProperty("overall_league_W")
    private int matchesWon;
    @JsonProperty("overall_league_D")
    private int matchesDrawn;
    @JsonProperty("overall_league_L")
    private int matchesLost;
    @JsonProperty("overall_league_GF")
    private int goalsForwarded;
    @JsonProperty("overall_league_GA")
    private int goalsAccumulated;

    public int getPosition ()
    {
        return position;
    }

    public void setPosition (int position)
    {
        this.position = position;
    }

    public String getTeamname ()
    {
        return teamname;
    }

    public void setTeamname (String teamname)
    {
        this.teamname = teamname;
    }

    public int getMatchesPlayed ()
    {
        return matchesPlayed;
    }

    public void setMatchesPlayed (int matchesPlayed)
    {
        this.matchesPlayed = matchesPlayed;
    }

    public int getMatchesWon ()
    {
        return matchesWon;
    }

    public void setMatchesWon (int matchesWon)
    {
        this.matchesWon = matchesWon;
    }

    public int getMatchesDrawn ()
    {
        return matchesDrawn;
    }

    public void setMatchesDrawn (int matchesDrawn)
    {
        this.matchesDrawn = matchesDrawn;
    }

    public int getMatchesLost ()
    {
        return matchesLost;
    }

    public void setMatchesLost (int matchesLost)
    {
        this.matchesLost = matchesLost;
    }

    public int getGoalsForwarded ()
    {
        return goalsForwarded;
    }

    public void setGoalsForwarded (int goalsForwarded)
    {
        this.goalsForwarded = goalsForwarded;
    }

    public int getPoints ()
    {
        return points;
    }

    public void setPoints (int points)
    {
        this.points = points;
    }

    public int getGoalsAccumulated ()
    {
        return goalsAccumulated;
    }

    public void setGoalsAccumulated (int goalsAccumulated)
    {
        this.goalsAccumulated = goalsAccumulated;
    }


}
