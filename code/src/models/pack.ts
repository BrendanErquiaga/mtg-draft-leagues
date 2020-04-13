import { Card } from ".";

export const DEFAULT_PACK_SIZE: number = 15;
export const WORDS_IN_PACK_NAME: number = 2;
export const PACK_ITEM_PREFIX: string = "packs/";

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

  public getS3Key(draftName:string) {
    return PACK_ITEM_PREFIX + draftName + '/' + this.name;
  }

  public getS3String() {
    return JSON.stringify(this);
  }
}