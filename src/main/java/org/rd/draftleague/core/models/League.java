package org.rd.draftleague.core.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "leagues")
public class League implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "leagueName", nullable = false)
    private String name;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @ManyToMany(mappedBy = "leagues", fetch = FetchType.EAGER)
    private List<Player> players;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", players=" + players +
                '}';
    }

    public League() {
    }

    public League(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public League(String name, Date startDate, List<Player> players) {
        this.name = name;
        this.startDate = startDate;
        this.players = players;
    }

    public void addPlayer(Player player) {
        this.getPlayers().add(player);
    }

    public void copyLeagueValues(League league) {
        this.name = league.getName();
        this.startDate = league.getStartDate();
        this.players = league.getPlayers();
    }
}
