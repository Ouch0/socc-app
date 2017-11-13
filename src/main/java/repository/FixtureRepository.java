package repository;

import com.soccer.soccer.Fixture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alina on 11/13/2017.
 */
@Repository
public interface FixtureRepository extends CrudRepository<Fixture, Integer>{
}
