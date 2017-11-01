package org.rd.draftleague.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "players")
@NamedQueries({
        @NamedQuery(name = "org.rd.draftleague.core.model.Player.findAll",
                query = "select e from Player e"),
        @NamedQuery(name = "org.rd.draftleague.core.model.Player.findByName",
                query = "select e from Player e "
                        + "where e.name like :name "
                        + "or e.nickName like :name")
})
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "playerName", nullable = false)
    private String name;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"players", "drafts"})
    private List<League> leagues;

    @ManyToMany(mappedBy = "draftPlayers")
    @JsonIgnoreProperties({"league", "turnOrderMovingTowardsDoublePick", "draftPlayers", "pickCount"})
    private List<Draft> drafts;

    public Player() {
    }

    public Player(String name, String nickName, String email, Date startDate, List<League> leagues, List<Draft> drafts) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.startDate = startDate;
        this.leagues = leagues;
        this.drafts = drafts;
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
}
