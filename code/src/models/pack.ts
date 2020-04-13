import { Card } from ".";

export const DEFAULT_PACK_SIZE: number = 15;

export interface PackDetail {
  name: string;
  cards: Card[];
}

export class Pack implements PackDetail {
  name: string;
  cards: Card[];
  openCount?: number | undefined;

  constructor(packDetail: PackDetail) {
    this.name = packDetail.name;
    this.cards = packDetail.cards;
    this.openCount = 0;
  }
}