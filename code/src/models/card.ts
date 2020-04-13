export interface CardDetail {
  cardName: string;
}

export class Card implements CardDetail {
  cardName: string;
  imageLink?: string | undefined;
  
  constructor(cardDetail: CardDetail) {
    this.cardName = cardDetail.cardName;
  }
}