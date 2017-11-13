package controller;

import com.soccer.soccer.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Alina on 11/13/2017.
 */
public class Util {

    private static final String file_name = "D:\\betting\\soccer\\premier-league-2016-2017_results.csv";
    private static final String competition_name = "Premier League";
    private static final String competition_season = "2016-2017";


    public static void getDataFromFile(Session sess) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(file_name)));

        String line = "line";
        while (line != "") {
            line = br.readLine();
            String[] str = line.split(",");

            sess.beginTransaction();
            Country england = new Country();
            Team team = new Team();
            Team team2 = new Team();
            Fixture fixture = new Fixture();
            Competition competition = new Competition();
            england.setName("England");
            Criteria countryCriteria = sess.createCriteria(Country.class);
            countryCriteria.add(Restrictions.eq("name", england.getName()));
            List<Country> countries = (List<Country>) countryCriteria.list();

//            CriteriaBuilder builder = sess.getCriteriaBuilder();
//            CriteriaQuery<Country> qry = builder.createQuery(Country.class);
//            Root<Country> root = qry.from( Country.class );
//            qry.select( root.get( Country_.name ) );
//            qry.where( builder.equal( root.get( Country_.name ), "John Doe" ) );

//            List<String> nickNames = sess.createQuery( qry.toString() ).getResultList();

            if (countries.size() == 0) {
                sess.save(england);
                team.setCountry(england);
                team2.setCountry(england);
                competition.setCountry(england);
            } else {
                team.setCountry(countries.get(0));
                team2.setCountry(countries.get(0));
                competition.setCountry(countries.get(0));
            }

            competition.setName(competition_name);
            competition.setSeason(competition_season);
            Criteria competititionCriteria = sess.createCriteria(Competition.class);
            competititionCriteria.add(Restrictions.eq("name", competition.getName()));
            competititionCriteria.add(Restrictions.eq("season", competition.getSeason()));
            List<Competition> competitions = (List<Competition>) competititionCriteria.list();

            if (competitions.size() == 0) {
                sess.save(competition);
                fixture.setCompetition(competition);
            } else {
                fixture.setCompetition(competitions.get(0));
            }


            team.setName(str[1]);

            Criteria teamCriteria = sess.createCriteria(Team.class);
            teamCriteria.add(Restrictions.eq("name", team.getName()));
            List<Team> teams = (List<Team>) teamCriteria.list();

            if (teams.size() == 0) {
                sess.save(team);
                fixture.setHomeTeam(team);
            } else {
                fixture.setHomeTeam(teams.get(0));
            }


            sess.getTransaction().commit();
            sess.beginTransaction();

            team2.setName(str[2]);

            Criteria team2Criteria = sess.createCriteria(Team.class);
            team2Criteria.add(Restrictions.eq("name", team2.getName()));
            List<Team> teams2 = (List<Team>) team2Criteria.list();

            if (teams2.size() == 0) {
                sess.save(team2);
                fixture.setAwayTeam(team2);
            } else {
                fixture.setAwayTeam(teams2.get(0));
            }
            sess.getTransaction().commit();

            sess.beginTransaction();


            try {
                fixture.setDatePlayed(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(str[3]));
            } catch (ParseException e) {
                e.printStackTrace();
            }


            fixture.setHomeScore(Integer.parseInt(str[4]));
            fixture.setAwayScore(Integer.parseInt(str[5]));
            fixture.setHomeScore1stRound(Integer.parseInt(str[7]));
            fixture.setAwayScore1stRound(Integer.parseInt(str[8]));
            fixture.setHomeScore2ndRound(Integer.parseInt(str[9]));
            fixture.setAwayScore2ndRound(Integer.parseInt(str[10]));

            Odds odds = new Odds();
            odds.setFixture_id(fixture);
            odds.setOddsAvgHome(Double.parseDouble(str[11]));
            odds.setOddsAvgDraw(Double.parseDouble(str[12]));
            odds.setOddsAvgAway(Double.parseDouble(str[13]));
            odds.setOddsMaxHome(Double.parseDouble(str[15]));
            odds.setOddsMaxDraw(Double.parseDouble(str[16]));
            odds.setOddsMaxAway(Double.parseDouble(str[17]));
            fixture.setOdds(odds);

            sess.save(fixture);
            sess.save(odds);
            sess.getTransaction().commit();
        }


    }

    public static void calculateElo(Session sess){
        Criteria allFixt = sess.createCriteria(Fixture.class);
        List<Fixture> allFixtures = (List<Fixture>) allFixt.list();

        for (Fixture f : allFixtures) {
            sess.beginTransaction();
            Criteria teamHome = sess.createCriteria(Team.class);
            teamHome.add(Restrictions.eq("id", f.getHomeTeam().getId()));
            Team homeTeam = ((List<Team>) teamHome.list()).get(0);

            Criteria teamAway = sess.createCriteria(Team.class);
            teamAway.add(Restrictions.eq("id", f.getAwayTeam().getId()));
            Team awayTeam = ((List<Team>) teamAway.list()).get(0);

            if (homeTeam.getEloHome() == 0) {
                homeTeam.setEloHome(1500);
            }
            if (awayTeam.getEloAway() == 0) {
                awayTeam.setEloAway(1500);
            }
            if (!(f.getEloAway() > 0) || !(f.getEloHome() > 0)) {
                EloCalculation.calculateElo(homeTeam, awayTeam, f.getHomeScore(), f.getAwayScore());
            }
            homeTeam.setElo((homeTeam.getEloHome() + homeTeam.getEloAway()) / 2);
            awayTeam.setElo((awayTeam.getEloHome() + awayTeam.getEloAway()) / 2);
            sess.saveOrUpdate(homeTeam);
            sess.saveOrUpdate(awayTeam);

            f.setEloHome(homeTeam.getEloHome());
            f.setEloAway(awayTeam.getEloAway());
            sess.saveOrUpdate(f);

            sess.getTransaction().commit();

        }
    }

    public static HashMap<Team, Odds> getMediumOdds() throws NoSuchMethodException {

        HashMap<Team, Odds> mediumOdds = new HashMap<>();

//        fixtureRepository
//                .findAll()
//                .forEach(fixture -> mediumOdds.put(fixture.getHomeTeam(), oddsRepository.findOne(fixture.getId())));
         return mediumOdds;
    }
}
