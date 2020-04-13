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

    await SaveDraftToS3(createdDraft);

    return { statusCode: 200, body: createdDraft.getHTTPString() };
  } catch (error) {
    console.error(error);
    return { statusCode: 500, body: JSON.stringify(error) };
  }
};

async function SaveDraftToS3(draft:Draft) {
  let savePromises = [];
  let saveDraftPromise = SaveItemToS3(BUCKET_NAME, draft.getS3Key(), draft.getS3String(), s3Client);
  savePromises.push(saveDraftPromise);

  draft.packs.forEach(pack => {
    let savePackPromise = SaveItemToS3(BUCKET_NAME, pack.getS3Key(draft.name), pack.getS3String(), s3Client);
    savePromises.push(savePackPromise);
  })

  await Promise.all(savePromises);
}