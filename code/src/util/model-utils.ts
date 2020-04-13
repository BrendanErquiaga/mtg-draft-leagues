import { Card, Pack, DEFAULT_SHUFFLE_COUNT } from "../models";
import * as uuid from 'uuid';

export function GetRandomDraftName(): string {
  return uuid.v4();
}

export function GetRandomPackName(): string {
  return uuid.v4();
}

export function CreateCardList(cardInputString:string, cardSeparatorChar?:string):Card[] {
  let cards: Card[] = [];

  if(cardInputString != "") {
    let seperatorChar = cardSeparatorChar || "\n";
    let splitCardList = cardInputString.split(seperatorChar);

    for(let i = 0; i < splitCardList.length; i++){
      cards.push(new Card({
        cardName: splitCardList[i]
      }));
    }
  }

  return cards;
}

export function CreatePacks(cards: Card[], packSize: number, numOfShuffles?: number):Pack[] {
  let packs:Pack[] = [];
  let shuffledCards = ShuffleCards(cards, numOfShuffles);

  let currentCards = [];

  for(let i = 0; i < shuffledCards.length; i++) {
    currentCards.push(shuffledCards[i]);

    if(currentCards.length >= packSize || i === shuffledCards.length - 1) {
      packs.push(new Pack({
        name: GetRandomPackName(),
        cards: currentCards
      }));

      currentCards = [];
    }
  }

  return packs;
}

export function ShuffleCards(cards: Card[], numOfShuffles?:number): Card[] {
  let shuffledCards = cards.slice();
  let shuffleCount = numOfShuffles || DEFAULT_SHUFFLE_COUNT;

  for (let i = 0; i < shuffleCount; i++) {
    shuffledCards = CardShuffle(shuffledCards);
  }

  return shuffledCards;
}

function CardShuffle(cards: Card[]): Card[] {
  //https://bost.ocks.org/mike/shuffle/
  //"FISHER YATES SHUFFLE"
  let m = cards.length, t, i;
  // While there remain elements to shuffle…
  while (m) {

    // Pick a remaining element…
    i = Math.floor(Math.random() * m--);

    // And swap it with the current element.
    t = cards[m];
    cards[m] = cards[i];
    cards[i] = t;
  }

  return cards;
}
