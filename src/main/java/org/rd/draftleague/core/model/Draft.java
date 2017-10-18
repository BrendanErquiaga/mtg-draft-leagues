package org.rd.draftleague.core.model;

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
    private int id;

    @Column(name = "draftName", nullable = false)
    private String name;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @ManyToOne
    private League league;

    @Enumerated(EnumType.STRING)
    private DraftFormat draftFormat;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean turnOrderMovingTowardsDoublePick;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Player> draftPlayers;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int draftedCardsCount;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int roundNumber;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int pickCount;

    @ManyToOne
    private CardList banList;

    public Draft() { }

    public Draft(String name, League league) {
        this(name, new Date(), league);
    }

    public Draft(String name, Date startDate, League league) {
        this(name, startDate, league, DraftFormat.ROTISSERIE_ROTATING_SNAKE);
    }

    public Draft(String name, Date startDate, League league, DraftFormat draftFormat) {
        this(name, startDate, league, draftFormat, new ArrayList<>());
    }

    public Draft(String name, Date startDate, League league, DraftFormat draftFormat, List<Player> draftPlayers) {
        this(name, startDate, league, draftFormat, draftPlayers, null);
    }

    public Draft(String name, Date startDate, League league, DraftFormat draftFormat, List<Player> draftPlayers, CardList banList) {
        this.name = name;
        this.startDate = startDate;
        this.league = league;
        this.draftFormat = draftFormat;
        this.draftPlayers = draftPlayers;
        this.banList = banList;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public League getLeague() { return league; }

    public void setLeague(League league) { this.league = league; }

    public DraftFormat getDraftFormat() {
        return draftFormat;
    }

    public void setDraftFormat(DraftFormat draftFormat) {
        this.draftFormat = draftFormat;
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

    public boolean isTurnOrderMovingTowardsDoublePick() {
        return turnOrderMovingTowardsDoublePick;
    }

    public void setTurnOrderMovingTowardsDoublePick(boolean turnOrderMovingTowardsDoublePick) {
        this.turnOrderMovingTowardsDoublePick = turnOrderMovingTowardsDoublePick;
    }

    public List<Player> getDraftPlayers() { return draftPlayers; }

    public void setDraftPlayers(List<Player> draftPlayers) { this.draftPlayers = draftPlayers; }

    public CardList getBanList() {
        return banList;
    }

    public String getBanListName() {
        return (getBanList() != null) ? getBanList().getName() : "";
    }

    public void setBanList(CardList banList) {
        this.banList = banList;
    }

    @Override
    public String toString() {
        return "Draft{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", league=" + league +
                ", draftFormat=" + draftFormat +
                ", draftedCardsCount=" + draftedCardsCount +
                ", roundNumber=" + roundNumber +
                ", pickCount=" + pickCount +
                ", turnOrderMovingTowardsDoublePick=" + turnOrderMovingTowardsDoublePick +
                ", draftPlayers=" + draftPlayers +
                ", banList=" + getBanListName() +
                '}';
    }

    public void addPlayer(Player player) {
        this.getDraftPlayers().add(player);
    }
}
