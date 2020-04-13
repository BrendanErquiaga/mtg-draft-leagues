import { Pack, DEFAULT_PACK_SIZE, Card } from ".";
import { CreatePacks } from "../util";

export const DEFAULT_SHUFFLE_COUNT: number = 10;
export const DRAFT_ITEM_PREFIX: string = "drafts/";

export interface DraftDetail {
  name: string;
  cardList: Card[];
  packSize?: number;
}

export enum DraftStatus {
  CREATED,
  DRAFTING,
  COMPLETE
}

export enum DraftType {
  CUBE
}

export class Draft implements DraftDetail {
  name: string;
  packs: Pack[];
  cardList: Card[];
  packSize: number;
  packCount: number;
  status: DraftStatus;
  type: DraftType;

  constructor(draftDetails: DraftDetail) {
    this.name = draftDetails.name;
    this.cardList = draftDetails.cardList;
    
    this.packSize = draftDetails.packSize || DEFAULT_PACK_SIZE;
    this.status = DraftStatus.CREATED;
    this.type = DraftType.CUBE;

    this.packs = CreatePacks(this.cardList, this.packSize);
    this.packCount = this.packs.length;
  }

  public getS3Key() {
    return DRAFT_ITEM_PREFIX + this.name;
  }
  public getS3String() {
    return JSON.stringify(this);
  }
}