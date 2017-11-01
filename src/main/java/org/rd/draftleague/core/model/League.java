package org.rd.draftleague.core.model;

import com.fasterxml.jackson.annotation.*;

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

    @ManyToMany(mappedBy = "leagues", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"email", "startDate", "leagues", "drafts"})
    private List<Player> players;

    @OneToMany(mappedBy = "league")
    @JsonIgnoreProperties({"league", "turnOrderMovingTowardsDoublePick", "draftPlayers", "pickCount"})
    private List<Draft> drafts;

    public League() {
    }

    public League(String name, Date startDate, List<Player> players, List<Draft> drafts) {
        this.name = name;
        this.startDate = startDate;
        this.players = players;
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

    public List<Draft> getDrafts() {
        return drafts;
    }

    public void setDrafts(List<Draft> drafts) {
        this.drafts = drafts;
    }
}
