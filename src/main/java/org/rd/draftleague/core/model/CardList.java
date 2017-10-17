package org.rd.draftleague.core.model;

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
    private int id;

    @Column(name = "cardlistName", nullable = false)
    private String name;

    @Column(name = "listCreationDate", nullable = false)
    private Date listCreationDate;

    @ManyToMany
    private List<Card> cards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    }

    public CardList(String name, Date listCreationDate) {
        this(name, listCreationDate, new ArrayList<>());
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
