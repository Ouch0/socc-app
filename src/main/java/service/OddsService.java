package service;

import com.soccer.soccer.Odds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OddsRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alina on 11/13/2017.
 */
@Service
public class OddsService {
    List<Odds> odds = new ArrayList<>();

    @Autowired
    private OddsRepository oddsRepository;

    public List<Odds> getAllOdds(){
        oddsRepository.findAll().forEach(odds::add);
        return odds;
    }

    public Odds getOdds(int id){
        return oddsRepository.findOne(id);
    }

    public Odds getOdds(Odds odds){
        return oddsRepository.findOne(odds.getFixture_id().getId());
    }

    @Transactional
    public void saveOdds(Odds odd){
        oddsRepository.save(odd);
    }

    @Transactional
    public void deleteOdds(int id){
        oddsRepository.delete(id);
    }
}
