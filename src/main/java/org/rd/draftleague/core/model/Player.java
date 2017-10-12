package org.rd.draftleague.core.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "players")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "playerName", nullable = false)
    private String name;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<League> leagues;

    @ManyToMany(mappedBy = "draftPlayers")
    private List<Draft> drafts;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public List<Draft> getDrafts() {
        return drafts;
    }

    public void setDrafts(List<Draft> drafts) {
        this.drafts = drafts;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", startDate=" + startDate +
                ", leagues=" + leagues.size() +
                ", drafts=" + drafts.size() +
                '}';
    }

    public Player() {
    }

    public Player(String name, String email, Date startDate) {
        this(name, "", email, startDate);
    }

    public Player(String name, String nickName, String email, Date startDate) {
        this(name, nickName, email, startDate, new ArrayList<>());
    }

    public Player(String name, String nickName, String email, Date startDate, List<League> leagues) {
        this(name, nickName, email, startDate, leagues, new ArrayList<>());
    }

    public Player(String name, String nickName, String email, Date startDate, List<League> leagues, List<Draft> drafts) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.startDate = startDate;
        this.leagues = leagues;
        this.drafts = drafts;
    }

    public void joinLeague(League leagueToJoin){
        this.getLeagues().add(leagueToJoin);
        leagueToJoin.addPlayer(this);
    }

    public void joinDraft(Draft draftToJoin){
        this.getDrafts().add(draftToJoin);
        draftToJoin.addPlayer(this);

        //TODO Only let this happen if they are in the same league as the draft
        //TODO Or auto-join the league if that feature is enabled
    }

    public void copyPlayerValues(Player playerToCopyFrom){
        this.name = playerToCopyFrom.getName();
        this.nickName = playerToCopyFrom.getNickName();
        this.email = playerToCopyFrom.getEmail();
        this.startDate = playerToCopyFrom.getStartDate();
        this.leagues = playerToCopyFrom.getLeagues();
    }
}
