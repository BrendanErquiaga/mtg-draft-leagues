package org.rd.draftleague.core.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.rd.draftleague.core.utils.DraftLeagueConstants;
import org.rd.draftleague.core.utils.DraftLeagueConstants.DraftFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "drafts")
public class Draft implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "draftName", nullable = false)
    private String name;

    @Column(name = "startDate")
    private Date startDate;

    @ManyToOne
    @JsonIgnoreProperties({"players", "drafts"})
    private League league;

    @Enumerated(EnumType.STRING)
    private DraftFormat draftFormat;

    @ManyToMany(mappedBy = "drafts")
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

    @Column(nullable = false, columnDefinition = "int default 50")
    private int roundLimit;

    @ManyToOne
    @JsonIgnoreProperties({"cards", "listCreationDate"})
    private CardList banList;

    @OneToMany(cascade=CascadeType.ALL)
    private List<DraftedCard> draftedCards;

    @Enumerated(EnumType.STRING)
    private DraftLeagueConstants.DraftStatus draftStatus;

    public Draft() {
        this.startDate = new Date();
    }

    public Draft(String name, League league, DraftFormat draftFormat, List<Player> draftPlayers, boolean turnOrderMovingTowardsDoublePick, int draftedCardsCount, int roundNumber, int pickCount, int roundLimit, CardList banList, List<DraftedCard> draftedCards) {
        this.name = name;
        this.league = league;
        this.draftFormat = draftFormat;
        this.draftPlayers = draftPlayers;
        this.turnOrderMovingTowardsDoublePick = turnOrderMovingTowardsDoublePick;
        this.draftedCardsCount = draftedCardsCount;
        this.roundNumber = roundNumber;
        this.pickCount = pickCount;
        this.roundLimit = roundLimit;
        this.banList = banList;
        this.draftedCards = draftedCards;
        this.draftStatus = DraftLeagueConstants.DraftStatus.CREATED;
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

    public List<DraftedCard> getDraftedCards() {
        return draftedCards;
    }

    public void setDraftedCards(List<DraftedCard> draftedCards) {
        this.draftedCards = draftedCards;
    }

    public int getRoundLimit() {
        return roundLimit;
    }

    public void setRoundLimit(int roundLimit) {
        this.roundLimit = roundLimit;
    }

    public DraftLeagueConstants.DraftStatus getDraftStatus() {
        return draftStatus;
    }

    public void setDraftStatus(DraftLeagueConstants.DraftStatus draftStatus) {
        this.draftStatus = draftStatus;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(!(o instanceof Draft)) {
            return false;
        }

        final Draft that = (Draft) o;

        return Objects.equals(this.hashCode(), that.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate);
    }

    public Draft updateDraft(Draft draft) {
        this.name = draft.getName();
        this.league = draft.getLeague();
        this.draftFormat = draft.getDraftFormat();
        this.turnOrderMovingTowardsDoublePick = draft.isTurnOrderMovingTowardsDoublePick();
        this.draftPlayers = draft.getDraftPlayers();
        this.draftedCardsCount = draft.getDraftedCardsCount();
        this.roundNumber = draft.getRoundNumber();
        this.pickCount = draft.getPickCount();
        this.banList = draft.getBanList();
        this.draftedCards = draft.getDraftedCards();
        this.roundLimit = draft.getRoundLimit();
        this.draftStatus = draft.getDraftStatus();

        return this;
    }

    public void joinLeauge(League league) {
        if(this.getLeague() != league) {
            this.setLeague(league);
        } else {
            //TODO Logs...
        }
    }

    public void addBanlist(CardList banList) {
        if(this.getBanList() != banList) {
            this.setBanList(banList);
        } else {
            //TODO Logs...
        }
    }

    public void draftCardForPlayer(Card card, Long playerId) {
        if(this.getDraftStatus() == DraftLeagueConstants.DraftStatus.DRAFTING) {
            if(playerIsInDraft(playerId)) {
                //TODO Make sure card isn't on the banlist
                //TODO Make sure card hasn't been drafted already

                DraftedCard draftedCard = new DraftedCard(card, new Date(), playerId);

                this.getDraftedCards().add(draftedCard);
                this.advanceDraft(playerId);
            } else {
                //Don't draft cards if the player isn't in your draft
            }

        } else {
            //Don't draft cards if it's not drafting time...
        }
    }

    private boolean playerIsInDraft(Long playerId) {
        for(Player player : this.getDraftPlayers()) {
            if(player.getId() == playerId) {
                return true;
            }
        }

        return false;
    }

    private void incrementDraftCounters() {
        this.setDraftedCardsCount(this.getDraftedCardsCount() + 1);
        this.setPickCount(this.getPickCount() + 1);
    }

    private void advanceDraft(Long lastDrafterId) {
        this.incrementDraftCounters();

        //Check if count has advanced enough to change draft direction
        //Advance round count if needed
        //Pick next drafter
    }
}
