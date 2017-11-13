package repository;

import com.soccer.soccer.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alina on 8/28/2017.
 */
@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

}
