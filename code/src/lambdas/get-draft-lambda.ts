import { S3 } from 'aws-sdk';
import { GetItemFromS3 } from '../util/s3-util';
import { DRAFT_ITEM_PREFIX, Draft } from '../models';
const s3Client: S3 = new S3();
const BUCKET_NAME: string = process.env.BUCKET_NAME || '';

//GET at api/draft/{id}
export const handler = async (event: any = {}): Promise<any> => {
  try {
    const requestedItemId = event.pathParameters.id;
    if (!requestedItemId) {
      return { statusCode: 400, body: `Error: You are missing the path parameter id` };
    }

    let s3ResponseObject = await GetItemFromS3(BUCKET_NAME, DRAFT_ITEM_PREFIX + requestedItemId, s3Client);

    if(!s3ResponseObject.Body) {
      return { statusCode: 500, body: "Object had no body. Requested Key: " + requestedItemId };
    } 

    let responseBody = s3ResponseObject.Body.toString();
    //TODO - Create Draft Object

    return { statusCode: 200, body: responseBody };
  } catch (error) {
    if(error.statusCode === 404) {
      return { statusCode: 404, body: "Specified key does not exist." };
    } else {
      return { statusCode: 500, body: JSON.stringify(error) };
    }
  }
};