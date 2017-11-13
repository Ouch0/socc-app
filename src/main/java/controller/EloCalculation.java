package controller;

import com.soccer.soccer.Team;

/**
 * Created by Alina on 8/16/2017.
 */
public class EloCalculation {
    public static void calculateElo(Team homeTeam, Team awayTeam, int resultHome, int resultAway){
        int K = 20;
        int resultMatch = resultHome - resultAway;
        double dr = homeTeam.getEloHome() - awayTeam.getEloAway();
        double drA = awayTeam.getEloAway() - homeTeam.getEloHome();
        double W = resultMatch > 0 ? 1 : resultMatch == 0 ? 0.5 : 0;
        double WA = resultMatch < 0 ? 1 : resultAway == 0 ? 0.5 : 0;
        double We = 1/ Math.pow(10,(-dr+100)/400 + 1);
        double WeA = 1/ Math.pow(10,(-drA)/400 + 1);

        homeTeam.setEloHome((int)(homeTeam.getEloHome() + K * (W - We)));
        awayTeam.setEloAway((int)(awayTeam.getEloAway() + K * (WA - WeA)));
    }
}
