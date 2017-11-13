package controller;

import com.soccer.soccer.Fixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.FixtureService;

import java.util.List;

/**
 * Created by Alina on 11/13/2017.
 */
@RestController
@RequestMapping("/fixtures")
public class FixtureController {
    @Autowired
    private FixtureService fixtureService;

    @Autowired
    public FixtureController(){
        this.fixtureService = new FixtureService();
    }

    @Autowired
    public FixtureController(FixtureService fixtureService){
        this.fixtureService = fixtureService;
    }

    @RequestMapping("/fixtures")
    public List<Fixture> getAllFixtures() {
        return fixtureService.getAllFixtures();
    }

    @RequestMapping("/fixtures/{id}")
    public Fixture getFixtureById(@PathVariable int id) {
        return fixtureService.getFixture(id);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/fixtures")
    public void saveFixture(Fixture fixture) {
        fixtureService.saveFixture(fixture);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/fixtures/{id}")
    public void updateFixture(Fixture fixture) {
        fixtureService.saveFixture(fixture);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/fixtures/{id}")
    public void deleteFixture(@PathVariable int id) {
        fixtureService.deleteFixture(id);
    }
}
