package repository;

import com.soccer.soccer.Odds;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alina on 11/13/2017.
 */
@Repository
public interface OddsRepository extends CrudRepository<Odds, Integer>{
}
