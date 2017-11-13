package controller;

import com.soccer.soccer.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.TeamService;

import java.util.List;

/**
 * Created by Alina on 8/27/2017.
 */
@RestController
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @RequestMapping("/teams")
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @RequestMapping("/teams/{id}")
    public Team getTeamById(@PathVariable int id){
        return teamService.getTeam(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/teams/")
    public void saveTeam(Team team){
        teamService.saveTeam(team);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/teams/{id}")
    public void updateTeam(Team team){ teamService.saveTeam(team); }

    @RequestMapping(method = RequestMethod.DELETE, path = "teams/{id}")
    public void deleteTeam(@PathVariable int id){
        teamService.deleteTeam(id);
    }

}
