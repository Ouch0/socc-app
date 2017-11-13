package service;

import com.soccer.soccer.Fixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FixtureRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alina on 11/13/2017.
 */
@Service
public class FixtureService {
    private List<Fixture> fixtures = new ArrayList<>();
    @Autowired
    private FixtureRepository fixtureRepository;

    @Autowired
    public FixtureService(){
    }

    @Autowired
    public FixtureService(FixtureRepository fixtureRepository){
        this.fixtureRepository = fixtureRepository;
    }

    public List<Fixture> getAllFixtures() {
        fixtureRepository.findAll().forEach(fixtures::add);
        return fixtures;
    }

    public Fixture getFixture(int id) {
        return fixtureRepository.findOne(id);
    }

    @Transactional
    public void saveFixture(Fixture fixture) {
        fixtureRepository.save(fixture);
    }

    @Transactional
    public void deleteFixture(int id) { fixtureRepository.delete(id); }
}
