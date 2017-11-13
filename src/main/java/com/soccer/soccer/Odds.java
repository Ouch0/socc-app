package com.soccer.soccer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by Alina on 8/14/2017.
 */
@Entity
public class Odds implements Serializable{

    @Id
    @OneToOne
    @JoinColumn(name = "fixture_id")
    private Fixture fixture_id;
    private double oddsAvgHome;
    private double oddsAvgDraw;
    private double oddsAvgAway;
    private double payoutAvg;
    private double oddsMaxHome;
    private double oddsMaxDraw;
    private double oddsMaxAway;
    private double payoutMax;

    public Odds() {
    }

    public Odds(Fixture fixture_id, double oddsAvgHome, double oddsAvgDraw, double oddsAvgAway, double payoutAvg, double oddsMaxHome, double oddsMaxDraw, double oddsMaxAway, double payoutMax) {
        this.fixture_id = fixture_id;
        this.oddsAvgHome = oddsAvgHome;
        this.oddsAvgDraw = oddsAvgDraw;
        this.oddsAvgAway = oddsAvgAway;
        this.payoutAvg = payoutAvg;
        this.oddsMaxHome = oddsMaxHome;
        this.oddsMaxDraw = oddsMaxDraw;
        this.oddsMaxAway = oddsMaxAway;
        this.payoutMax = payoutMax;
    }

    public Fixture getFixture_id() {
        return fixture_id;
    }

    public void setFixture_id(Fixture fixture_id) {
        this.fixture_id = fixture_id;
    }

    public double getOddsAvgHome() {
        return oddsAvgHome;
    }

    public void setOddsAvgHome(double oddsAvgHome) {
        this.oddsAvgHome = oddsAvgHome;
    }

    public double getOddsAvgDraw() {
        return oddsAvgDraw;
    }

    public void setOddsAvgDraw(double oddsAvgDraw) {
        this.oddsAvgDraw = oddsAvgDraw;
    }

    public double getOddsAvgAway() {
        return oddsAvgAway;
    }

    public void setOddsAvgAway(double oddsAvgAway) {
        this.oddsAvgAway = oddsAvgAway;
    }

    public double getOddsMaxHome() {
        return oddsMaxHome;
    }

    public void setOddsMaxHome(double oddsMaxHome) {
        this.oddsMaxHome = oddsMaxHome;
    }

    public double getOddsMaxDraw() {
        return oddsMaxDraw;
    }

    public void setOddsMaxDraw(double oddsMaxDraw) {
        this.oddsMaxDraw = oddsMaxDraw;
    }

    public double getOddsMaxAway() {
        return oddsMaxAway;
    }

    public void setOddsMaxAway(double oddsMaxAway) {
        this.oddsMaxAway = oddsMaxAway;
    }

    public double getPayoutAvg() {
        return payoutAvg;
    }

    public void setPayoutAvg(double payoutAvg) {
        this.payoutAvg = payoutAvg;
    }

    public double getPayoutMax() {
        return payoutMax;
    }

    public void setPayoutMax(double payoutMax) {
        this.payoutMax = payoutMax;
    }
}
