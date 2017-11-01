package org.rd.draftleague.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
