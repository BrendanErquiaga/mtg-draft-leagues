import { S3 } from 'aws-sdk';
export const ITEM_KEY_SUFFIX: string = '.json';

export async function SaveItemToS3(bucketName: string, itemKey: string, itemString: string, s3Client:S3) {
  let putObjectRequest: S3.PutObjectRequest = {
    Bucket: bucketName,
    Key: itemKey + ITEM_KEY_SUFFIX,
    Body: itemString
  }

  let s3Response = await s3Client.putObject(putObjectRequest).promise();

  return s3Response;
}

export async function GetItemFromS3(bucketName: string, itemKey: string, s3Client: S3) {
  let getObjectRequest: S3.GetObjectRequest = {
    Bucket: bucketName,
    Key: itemKey + ITEM_KEY_SUFFIX
  }

  let s3Response = await s3Client.getObject(getObjectRequest).promise();

  return s3Response;
}