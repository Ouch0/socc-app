package service;

import com.soccer.soccer.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TeamRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alina on 8/27/2017.
 */
@Service
public class TeamService {

    private List<Team> teams;
    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        teams = new ArrayList<>();
        teamRepository.findAll().forEach(teams::add);
        return teams;
    }

    public Team getTeam(int id) {
        return teamRepository.findOne(id);
    }

    @Transactional
    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    @Transactional
    public void deleteTeam(int id) { teamRepository.delete(id); }
}
