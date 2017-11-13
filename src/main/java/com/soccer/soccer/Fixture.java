package com.soccer.soccer;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Alina on 8/14/2017.
 */
@Entity
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
    private double eloHome;
    private double eloAway;
    private int homeScore1stRound;
    private int awayScore1stRound;
    private int homeScore2ndRound;
    private int awayScore2ndRound;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;
    private Date datePlayed;
    @OneToOne
    @Transient
    private Odds odds;

    public Fixture() {
    }

    public Fixture(Team homeTeam, Team awayTeam, int homeScore, int awayScore, double eloHome, double eloAway, int homeScore1stRound, int awayScore1stRound, int homeScore2ndRound, int awayScore2ndRound, Competition competition, Date datePlayed, Odds odds) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.eloHome = eloHome;
        this.eloAway = eloAway;
        this.homeScore1stRound = homeScore1stRound;
        this.awayScore1stRound = awayScore1stRound;
        this.homeScore2ndRound = homeScore2ndRound;
        this.awayScore2ndRound = awayScore2ndRound;
        this.competition = competition;
        this.datePlayed = datePlayed;
        this.odds = odds;
    }

    public int getHomeScore1stRound() {
        return homeScore1stRound;
    }

    public void setHomeScore1stRound(int homeScore1stRound) {
        this.homeScore1stRound = homeScore1stRound;
    }

    public int getAwayScore1stRound() {
        return awayScore1stRound;
    }

    public void setAwayScore1stRound(int awayScore1stRound) {
        this.awayScore1stRound = awayScore1stRound;
    }

    public int getHomeScore2ndRound() {
        return homeScore2ndRound;
    }

    public void setHomeScore2ndRound(int homeScore2ndRound) {
        this.homeScore2ndRound = homeScore2ndRound;
    }

    public int getAwayScore2ndRound() {
        return awayScore2ndRound;
    }

    public void setAwayScore2ndRound(int awayScore2ndRound) {
        this.awayScore2ndRound = awayScore2ndRound;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public Date getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(Date datePlayed) {
        this.datePlayed = datePlayed;
    }

    public double getEloHome() {
        return eloHome;
    }

    public void setEloHome(double eloHome) {
        this.eloHome = eloHome;
    }

    public double getEloAway() {
        return eloAway;
    }

    public void setEloAway(double eloAway) {
        this.eloAway = eloAway;
    }

    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }
}
