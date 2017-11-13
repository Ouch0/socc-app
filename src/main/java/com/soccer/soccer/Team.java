package com.soccer.soccer;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alina on 8/14/2017.
 */
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @ManyToMany
    @JoinTable(name = "team_players", joinColumns = @JoinColumn(name = "team_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Player> players;
    @Column(name = "elohome")
    private int eloHome;
    @Column(name = "eloaway")
    private int eloAway;
    private int elo;

    public Team(){
    }

    public Team(String name, Country country, List<Player> players, int eloHome, int eloAway, int elo) {
        this.name = name;
        this.country = country;
        this.players = players;
        this.eloHome = eloHome;
        this.eloAway = eloAway;
        this.elo = elo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getEloHome() {
        return eloHome;
    }

    public void setEloHome(int eloHome) {
        this.eloHome = eloHome;
    }

    public int getEloAway() {
        return eloAway;
    }

    public void setEloAway(int eloAway) {
        this.eloAway = eloAway;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }
}
