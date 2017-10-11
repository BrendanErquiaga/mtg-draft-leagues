package org.rd.draftleague.core.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "players")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", startDate=" + startDate +
                ", leagues=" + leagues +
                '}';
    }

    public Player() {
    }

    public Player(String name, String email, Date startDate) {
        this.name = name;
        this.email = email;
        this.startDate = startDate;
    }

    public Player(String name, String nickName, String email, Date startDate) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.startDate = startDate;
    }

    public Player(String name, String nickName, String email, Date startDate, List<League> leagues) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.startDate = startDate;
        this.leagues = leagues;
    }

    public void joinLeague(League leagueToJoin){
        this.getLeagues().add(leagueToJoin);
    }

    public void copyPlayerValues(Player playerToCopyFrom){
        this.name = playerToCopyFrom.getName();
        this.nickName = playerToCopyFrom.getNickName();
        this.email = playerToCopyFrom.getEmail();
        this.startDate = playerToCopyFrom.getStartDate();
        this.leagues = playerToCopyFrom.getLeagues();
    }
}
