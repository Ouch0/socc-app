package controller;

import com.soccer.soccer.Odds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.OddsService;

import java.util.List;

/**
 * Created by Alina on 11/13/2017.
 */
@RestController
@RequestMapping("/odds")
public class OddsController {
    @Autowired
    private OddsService oddsService;

    @RequestMapping("/odds")
    public List<Odds> getAllOdds(){
        return oddsService.getAllOdds();
    }

    @RequestMapping("/odds/{id}")
    public Odds getOddsById(@PathVariable int id){
        return oddsService.getOdds(id);
    }

    public Odds getOdds(Odds odds){
        return oddsService.getOdds(odds);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/odds")
    public void saveOdds(Odds odd){ oddsService.saveOdds(odd);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/odds/{id}")
    public void updateOdds(Odds odd){ oddsService.saveOdds(odd); }

    @RequestMapping(method = RequestMethod.DELETE, path = "/odds/{id}")
    public void deleteOdds(@PathVariable int id){ oddsService.deleteOdds(id);
    }

}
