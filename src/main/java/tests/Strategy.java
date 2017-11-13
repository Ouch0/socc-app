package tests;

import com.soccer.soccer.Fixture;
import com.soccer.soccer.Odds;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alina on 9/22/2017.
 */
public class Strategy {

    public static void calculateOutcome(Session sess) throws IOException {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EMF_JPA");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Object> fixtureCriteriaQuery = criteria.createQuery();
//        TypedQuery<Object> fixtureTypedQuery = entityManager.createQuery(fixtureCriteriaQuery.select(fixtureCriteriaQuery.from(Fixture.class)));
//        List<Object> all = fixtureTypedQuery.getResultList();
        Criteria fixtureCriteria = sess.createCriteria(Fixture.class);
        //fixtureCriteria.add(Restrictions.eq("competition", sess.createCriteria(Competition.class).list().get(1)));
        List<Fixture> allFixtures = fixtureCriteria.list();
        Criteria oddsCriteria = sess.createCriteria(Odds.class);
        List<Odds> allOdds = oddsCriteria.list();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\betting\\soccer\\2014-2017_testall.csv"));
        double total = 100;
        int unit = 2;
        for (Fixture fixture : allFixtures) {
            String line = "";
            Odds odds = allOdds.get(fixture.getId() - 1);
            line += fixture.getId() + "," + fixture.getHomeTeam().getName() + "," + fixture.getEloHome() + "," + fixture.getHomeScore() + ",";
            line += fixture.getAwayTeam().getName() + "," + fixture.getEloAway() + "," +  fixture.getAwayScore() + ",";
            line += odds.getOddsAvgHome() + "," + odds.getOddsAvgDraw() + "," + odds.getOddsAvgAway() + ",";
            if( fixture.getEloHome() > fixture.getEloAway()){
                if(fixture.getHomeScore() > fixture.getAwayScore()){
                    total += unit*odds.getOddsAvgHome()-unit;
                }else{
                    total=total-unit;
                }
            }else{
                if(fixture.getAwayScore() > fixture.getHomeScore()) {
                    total += unit*odds.getOddsAvgAway()-unit;
                }else{
                    total=total-unit;
                }
            }
            line += total;
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }


    public static void calculateDraw(Session sess) throws IOException {
        Criteria fixtureCriteria = sess.createCriteria(Fixture.class);
        List<Fixture> allFixtures = fixtureCriteria.list();
        Criteria oddsCriteria = sess.createCriteria(Odds.class);
        List<Odds> allOdds = oddsCriteria.list();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\betting\\soccer\\2014-2015_testdraw.csv"));
        double total = 100;
        int unit = 1;
        for (Fixture fixture : allFixtures) {
            String line = "";
            Odds odds = allOdds.get(fixture.getId() - 1);
            line += fixture.getId() + "," + fixture.getHomeTeam().getName() + "," + fixture.getEloHome() + "," + fixture.getHomeScore() + ",";
            line += fixture.getAwayTeam().getName() + "," + fixture.getEloAway() + "," +  fixture.getAwayScore() + ",";
            line += odds.getOddsAvgHome() + "," + odds.getOddsAvgDraw() + "," + odds.getOddsAvgAway() + ",";

                if(fixture.getHomeScore() == fixture.getAwayScore()){
                    total += unit*odds.getOddsAvgHome()-unit;
                }else{
                    total=total-unit;
                }

            line += total;
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
