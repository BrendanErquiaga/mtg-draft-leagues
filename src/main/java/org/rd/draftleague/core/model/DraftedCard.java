package org.rd.draftleague.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "drafted-cards")
public class DraftedCard implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "draftedCardId")
    private Long draftedCardId;

    @ManyToOne
    @JsonIgnoreProperties({
            "manaCostString",
            "fullCardTypeString",
            "artifactType",
            "creatureType",
            "enchantmentType",
            "instantType",
            "landType",
            "legendaryType",
            "planeswalkerType",
            "sorceryType",
            "tribalType",
            "whiteColor",
            "blueColor",
            "blackColor",
            "redColor",
            "greenColor",
            "colorlessColor"
    })
    private Card cardReference;

    @Column(name = "draftTime")
    private Date draftTime;

    public Long getDraftedCardId() {
        return draftedCardId;
    }

    public void setDraftedCardId(Long draftedCardId) {
        this.draftedCardId = draftedCardId;
    }

    public Card getCardReference() {
        return cardReference;
    }

    public void setCardReference(Card cardReference) {
        this.cardReference = cardReference;
    }

    public Date getDraftTime() {
        return draftTime;
    }

    public void setDraftTime(Date draftTime) {
        this.draftTime = draftTime;
    }

    public DraftedCard() {
    }

    public DraftedCard(Long draftedCardId, Card cardReference, Date draftTime)
    {
        this.draftedCardId = draftedCardId;
        this.cardReference = cardReference;
        this.draftTime = draftTime;
    }

    public DraftedCard update(DraftedCard draftedCard)
    {
        this.draftedCardId = draftedCard.getDraftedCardId();
        this.cardReference = draftedCard.getCardReference();
        this.draftTime = draftedCard.getDraftTime();

        return this;
    }
}