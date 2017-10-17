package org.rd.draftleague.core.model;

import org.rd.draftleague.core.utils.DraftLeagueConstants.CardColors;
import org.rd.draftleague.core.utils.DraftLeagueConstants.CardType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardId")
    private int cardId;

    @Column(name = "cardName", nullable = false)
    private String name;

    @Column(nullable = false)
    private int convertedManaCost;

    private String manaCostString;

    private String fullCardTypeString;

//    @ElementCollection(targetClass = DraftLeagueConstants.class)
//    @CollectionTable(name = "cards_identity",
//            joinColumns = @JoinColumn(name = "APP_ID"))
//    @Column(name = "cardColorIdentity")
//    private Set<CardColors> cardColorIdentity;
//
//    @ElementCollection(targetClass = DraftLeagueConstants.class)
//    @CollectionTable()
//    @Column(name = "cardTypes")
//    private List<CardType> cardTypes;

    public Card() { }

    public Card(String name) {
        this(name, 1);
    }

    public Card(String name, int convertedManaCost) {
        this(name, convertedManaCost, "");
    }

    public Card(String name, int convertedManaCost, String fullCardTypeString) {
        this(name, convertedManaCost, "", fullCardTypeString);
    }

    public Card(String name, int convertedManaCost, String manaCostString, String fullCardTypeString) {
        this(name, convertedManaCost, manaCostString, fullCardTypeString, new ArrayList<>(), new ArrayList<>());
    }

    public Card(String name, int convertedManaCost, String manaCostString, String fullCardTypeString, List<CardColors> cardColorIdentity, List<CardType> cardTypes) {
        this.name = name;
        this.convertedManaCost = convertedManaCost;
        this.manaCostString = manaCostString;
        this.fullCardTypeString = fullCardTypeString;
//        this.cardColorIdentity = cardColorIdentity;
//        this.cardTypes = cardTypes;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConvertedManaCost() {
        return convertedManaCost;
    }

    public void setConvertedManaCost(int convertedManaCost) {
        this.convertedManaCost = convertedManaCost;
    }

    public String getManaCostString() {
        return manaCostString;
    }

    public void setManaCostString(String manaCostString) {
        this.manaCostString = manaCostString;
    }

    public String getFullCardTypeString() {
        return fullCardTypeString;
    }

    public void setFullCardTypeString(String fullCardTypeString) {
        this.fullCardTypeString = fullCardTypeString;
    }

//    public Set<CardColors> getCardColorIdentity() {
//        return cardColorIdentity;
//    }
//
//    public void setCardColorIdentity(Set<CardColors> cardColorIdentity) {
//        this.cardColorIdentity = cardColorIdentity;
//    }
//
//    public List<CardType> getCardTypes() {
//        return cardTypes;
//    }
//
//    public void setCardTypes(List<CardType> cardTypes) {
//        this.cardTypes = cardTypes;
//    }

//    @Override
//    public String toString() {
//        return "Card{" +
//                "cardId=" + cardId +
//                ", name='" + name + '\'' +
//                ", convertedManaCost=" + convertedManaCost +
//                ", manaCostString='" + manaCostString + '\'' +
//                ", fullCardTypeString='" + fullCardTypeString + '\'' +
//                ", cardColorIdentity=" + cardColorIdentity +
//                ", cardTypes=" + cardTypes +
//                '}';
//    }


    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", name='" + name + '}';
    }
}
