package org.rd.draftleague.core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "leagues")
public class League implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "leagueName", nullable = false)
    private String name;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

//    @ManyToMany(mappedBy = "leagues", fetch = FetchType.EAGER)
//    private List<Player> players;
//
//    @OneToMany(mappedBy = "league")
//    private List<Draft> drafts;

    public League() {
    }

    public League(String name, Date startDate) {
        this(name, startDate, new ArrayList<>());
    }

    public League(String name, Date startDate, List<Player> players) {
        this.name = name;
        this.startDate = startDate;
//        this.players = players;
//        this.drafts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

//    public List<Player> getPlayers() {
//        return players;
//    }
//
//    public void setPlayers(List<Player> players) {
//        this.players = players;
//    }
//
//    public List<Draft> getDrafts() {
//        return drafts;
//    }
//
//    public void setDrafts(List<Draft> drafts) {
//        this.drafts = drafts;
//    }
//
//    @Override
//    public String toString() {
//        return "League{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", startDate=" + startDate +
//                ", players=" + players.size() +
//                ", drafts=" + drafts.size() +
//                '}';
//    }
//
//    public void addPlayer(Player player) {
//        this.getPlayers().add(player);
//    }
//
//    public void addDraft(Draft draft) {
//        this.getDrafts().add(draft);
//    }
//
//    public void copyLeagueValues(League league) {
//        this.name = league.getName();
//        this.startDate = league.getStartDate();
//        this.players = league.getPlayers();
//        this.drafts = league.getDrafts();
//    }
}
