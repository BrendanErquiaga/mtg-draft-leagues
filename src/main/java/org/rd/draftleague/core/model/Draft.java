package org.rd.draftleague.core.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.rd.draftleague.core.utils.DraftLeagueConstants.DraftFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "drafts")
public class Draft implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "draftName", nullable = false)
    private String name;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @ManyToOne
    @JsonIgnoreProperties({"players", "drafts"})
    private League league;

    @Enumerated(EnumType.STRING)
    private DraftFormat draftFormat;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"email", "startDate", "leagues", "drafts"})
    private List<Player> draftPlayers;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean turnOrderMovingTowardsDoublePick;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int draftedCardsCount;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int roundNumber;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int pickCount;

    @ManyToOne
    @JsonIgnoreProperties({"cards", "listCreationDate"})
    private CardList banList;

    public Draft() {
    }

    public Draft(String name, Date startDate, League league, DraftFormat draftFormat, boolean turnOrderMovingTowardsDoublePick, List<Player> draftPlayers, int draftedCardsCount, int roundNumber, int pickCount, CardList banList) {
        this.name = name;
        this.startDate = startDate;
        this.league = league;
        this.draftFormat = draftFormat;
        this.turnOrderMovingTowardsDoublePick = turnOrderMovingTowardsDoublePick;
        this.draftPlayers = draftPlayers;
        this.draftedCardsCount = draftedCardsCount;
        this.roundNumber = roundNumber;
        this.pickCount = pickCount;
        this.banList = banList;
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public DraftFormat getDraftFormat() {
        return draftFormat;
    }

    public void setDraftFormat(DraftFormat draftFormat) {
        this.draftFormat = draftFormat;
    }

    public boolean isTurnOrderMovingTowardsDoublePick() {
        return turnOrderMovingTowardsDoublePick;
    }

    public void setTurnOrderMovingTowardsDoublePick(boolean turnOrderMovingTowardsDoublePick) {
        this.turnOrderMovingTowardsDoublePick = turnOrderMovingTowardsDoublePick;
    }

    public List<Player> getDraftPlayers() {
        return draftPlayers;
    }

    public void setDraftPlayers(List<Player> draftPlayers) {
        this.draftPlayers = draftPlayers;
    }

    public int getDraftedCardsCount() {
        return draftedCardsCount;
    }

    public void setDraftedCardsCount(int draftedCardsCount) {
        this.draftedCardsCount = draftedCardsCount;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getPickCount() {
        return pickCount;
    }

    public void setPickCount(int pickCount) {
        this.pickCount = pickCount;
    }

    public CardList getBanList() {
        return banList;
    }

    public void setBanList(CardList banList) {
        this.banList = banList;
    }
}
