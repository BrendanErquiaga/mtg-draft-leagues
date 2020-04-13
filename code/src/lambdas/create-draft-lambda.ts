import { S3 } from 'aws-sdk';
import { CreateCardList, GetRandomDraftName } from '../util';
import { Card, Draft } from '../models';
import { SaveItemToS3 } from '../util/s3-util';
const s3Client: S3 = new S3();
const BUCKET_NAME: string = process.env.BUCKET_NAME || '';


//POST at api/items
export const handler = async (event: any = {}): Promise<any> => {
  try {
    let cardList:Card[] = CreateCardList(event.body);
    let createdDraft = new Draft({
      name: GetRandomDraftName(),
      cardList: cardList
    });

    let s3Response = await SaveItemToS3(BUCKET_NAME, createdDraft.getS3Key(), createdDraft.getS3String(), s3Client);

    //TODO - Return Link to draft
    return { statusCode: 200, body: JSON.stringify(s3Response) };
  } catch (error) {
    console.error(error);
    return { statusCode: 500, body: JSON.stringify(error) };
  }
};