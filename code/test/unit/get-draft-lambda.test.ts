describe('Tests get one object', function () {
  const lambda = require('../../src/lambdas/get-draft-lambda');

  let event: any;
  beforeAll(() => {
    jest.resetModules();
    event = {
      pathParameters: {
        id: "foot-start-establish-concerned"
      }
    };
  });

  it('verifies successful response', async () => {
    const result = await lambda.handler(event);

    expect(result.statusCode).toEqual(200);
  });

  it('verifies not found response', async () => {
    const result = await lambda.handler({
      pathParameters: {
        id: "000000"
      }
    });

    expect(result.statusCode).toEqual(404);
  });
});
