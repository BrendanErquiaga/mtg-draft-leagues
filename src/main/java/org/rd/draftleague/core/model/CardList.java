package org.rd.draftleague.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cardlists")
public class CardList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cardlistName", nullable = false)
    private String name;

    @Column(name = "listCreationDate")
    private Date listCreationDate;

    @ManyToMany
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
    private List<Card> cards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return (name != null) ? name : "null";
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getListCreationDate() {
        return listCreationDate;
    }

    public void setListCreationDate(Date listCreationDate) {
        this.listCreationDate = listCreationDate;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public CardList() {
        this.listCreationDate = new Date();
    }

    public CardList(String name, Date listCreationDate, List<Card> cards) {
        this.name = name;
        this.listCreationDate = listCreationDate;
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "CardList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if(!(o instanceof CardList)) {
            return false;
        }

        final CardList that = (CardList) o;

        return Objects.equals(this.hashCode(), that.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, listCreationDate);
    }

    public CardList update(CardList cardList) {
        this.name = cardList.getName();
        this.cards = cardList.getCards();

        return this;
    }

    public void addCard(Card card) {
        if(!this.getCards().contains(card)) {
            this.getCards().add(card);
        } else {
            //TODO Add log warning about this?
        }
    }

    public boolean listContainsCard(Card cardToCheck) {
        return this.getCards().contains(cardToCheck);
    }
}
