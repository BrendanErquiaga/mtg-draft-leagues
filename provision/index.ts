import apigateway = require('@aws-cdk/aws-apigateway');
import s3 = require('@aws-cdk/aws-s3');
import lambda = require('@aws-cdk/aws-lambda');
import cdk = require('@aws-cdk/core');

export class DraftStack extends cdk.Stack {
  constructor(app: cdk.App, id: string) {
    super(app, id);

    const bucket = new s3.Bucket(this, "mtg-drafts");

    const createDraftLambda = new lambda.Function(this, 'createDraftLambda', {
      code: new lambda.AssetCode('../code'),
      handler: 'src/lambdas/create-draft-lambda.handler',
      runtime: lambda.Runtime.NODEJS_10_X,
      environment: {
        BUCKET_NAME: bucket.bucketName
      }
    });

    bucket.grantReadWrite(createDraftLambda);

    const api = new apigateway.RestApi(this, 'draftAPI', {
      restApiName: 'MTG Draft API'
    });

    const draft = api.root.addResource('draft');
    addCorsOptions(draft);
    const createDraftIntegration = new apigateway.LambdaIntegration(createDraftLambda);
    draft.addMethod('POST', createDraftIntegration);
  }
}

export function addCorsOptions(apiResource: apigateway.IResource) {
  apiResource.addMethod('OPTIONS', new apigateway.MockIntegration({
    integrationResponses: [{
      statusCode: '200',
      responseParameters: {
        'method.response.header.Access-Control-Allow-Headers': "'Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token,X-Amz-User-Agent'",
        'method.response.header.Access-Control-Allow-Origin': "'*'",
        'method.response.header.Access-Control-Allow-Credentials': "'false'",
        'method.response.header.Access-Control-Allow-Methods': "'OPTIONS,GET,PUT,POST,DELETE'",
      },
    }],
    passthroughBehavior: apigateway.PassthroughBehavior.NEVER,
    requestTemplates: {
      "application/json": "{\"statusCode\": 200}"
    },
  }), {
    methodResponses: [{
      statusCode: '200',
      responseParameters: {
        'method.response.header.Access-Control-Allow-Headers': true,
        'method.response.header.Access-Control-Allow-Methods': true,
        'method.response.header.Access-Control-Allow-Credentials': true,
        'method.response.header.Access-Control-Allow-Origin': true,
      },
    }]
  })
}

const app = new cdk.App();
new DraftStack(app, 'mtg-drafting-api');
app.synth();