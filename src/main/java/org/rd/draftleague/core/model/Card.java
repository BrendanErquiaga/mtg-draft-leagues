package org.rd.draftleague.core.model;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean artifactType;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean creatureType;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean enchantmentType;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean instantType;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean landType;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean legendaryType;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean planeswalkerType;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean sorceryType;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean tribalType;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean whiteColor;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean blueColor;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean blackColor;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean redColor;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean greenColor;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean colorlessColor;

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
        this(name, convertedManaCost, manaCostString, fullCardTypeString,
                false,false,false,false,false,false,false,false,false,
                false,false,false,false,false,false);
    }

    public Card(String name, int convertedManaCost, String manaCostString, String fullCardTypeString, boolean artifactType, boolean creatureType, boolean enchantmentType, boolean instantType, boolean landType, boolean legendaryType, boolean planeswalkerType, boolean sorceryType, boolean tribalType, boolean whiteColor, boolean blueColor, boolean blackColor, boolean redColor, boolean greenColor, boolean colorlessColor) {
        this.name = name;
        this.convertedManaCost = convertedManaCost;
        this.manaCostString = manaCostString;
        this.fullCardTypeString = fullCardTypeString;
        this.artifactType = artifactType;
        this.creatureType = creatureType;
        this.instantType = instantType;
        this.enchantmentType = enchantmentType;
        this.landType = landType;
        this.legendaryType = legendaryType;
        this.planeswalkerType = planeswalkerType;
        this.sorceryType = sorceryType;
        this.tribalType = tribalType;
        this.whiteColor = whiteColor;
        this.blueColor = blueColor;
        this.blackColor = blackColor;
        this.redColor = redColor;
        this.greenColor = greenColor;
        this.colorlessColor = colorlessColor;
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

    public boolean isCreatureType() {
        return creatureType;
    }

    public void setCreatureType(boolean creatureType) {
        this.creatureType = creatureType;
    }

    public boolean isEnchantmentType() {
        return enchantmentType;
    }

    public void setEnchantmentType(boolean enchantmentType) {
        this.enchantmentType = enchantmentType;
    }

    public boolean isArtifactType() {
        return artifactType;
    }

    public void setArtifactType(boolean artifactType) {
        this.artifactType = artifactType;
    }

    public boolean isInstantType() {
        return instantType;
    }

    public void setInstantType(boolean instantType) {
        this.instantType = instantType;
    }

    public boolean isLandType() {
        return landType;
    }

    public void setLandType(boolean landType) {
        this.landType = landType;
    }

    public boolean isLegendaryType() {
        return legendaryType;
    }

    public void setLegendaryType(boolean legendaryType) {
        this.legendaryType = legendaryType;
    }

    public boolean isPlaneswalkerType() {
        return planeswalkerType;
    }

    public void setPlaneswalkerType(boolean planeswalkerType) {
        this.planeswalkerType = planeswalkerType;
    }

    public boolean isSorceryType() {
        return sorceryType;
    }

    public void setSorceryType(boolean sorceryType) {
        this.sorceryType = sorceryType;
    }

    public boolean isTribalType() {
        return tribalType;
    }

    public void setTribalType(boolean tribalType) {
        this.tribalType = tribalType;
    }

    public boolean isWhiteColor() {
        return whiteColor;
    }

    public void setWhiteColor(boolean whiteColor) {
        this.whiteColor = whiteColor;
    }

    public boolean isBlueColor() {
        return blueColor;
    }

    public void setBlueColor(boolean blueColor) {
        this.blueColor = blueColor;
    }

    public boolean isBlackColor() {
        return blackColor;
    }

    public void setBlackColor(boolean blackColor) {
        this.blackColor = blackColor;
    }

    public boolean isRedColor() {
        return redColor;
    }

    public void setRedColor(boolean redColor) {
        this.redColor = redColor;
    }

    public boolean isGreenColor() {
        return greenColor;
    }

    public void setGreenColor(boolean greenColor) {
        this.greenColor = greenColor;
    }

    public boolean isColorlessColor() {
        return colorlessColor;
    }

    public void setColorlessColor(boolean colorlessColor) {
        this.colorlessColor = colorlessColor;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", name='" + name + '}';
    }
}
