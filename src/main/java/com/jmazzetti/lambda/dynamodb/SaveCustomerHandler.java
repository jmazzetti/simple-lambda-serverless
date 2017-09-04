package com.jmazzetti.lambda.dynamodb;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.jmazzetti.lambda.dynamodb.bean.CustomerRequest;
import com.jmazzetti.lambda.dynamodb.bean.CustomerResponse;

public class SaveCustomerHandler implements RequestHandler<CustomerRequest, CustomerResponse> {

    private DynamoDB dynamoDb;

    private String DYNAMODB_TABLE_NAME = "Customer";  // This is the table name added in AWS
    private Regions REGION = Regions.US_EAST_1;     // Be aware of US_EAST_2, it's not included in this version of AWS.

    public CustomerResponse handleRequest(CustomerRequest customerRequest, Context context) {
        this.initDynamoDbClient();

        persistData(customerRequest);

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setMessage("Saved Successfully!!!");
        return customerResponse;
    }

    private PutItemOutcome persistData(CustomerRequest personRequest) throws ConditionalCheckFailedException {
        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
          .putItem(
            new PutItemSpec().withItem(new Item()
              .withString("id", personRequest.getId())
              .withString("firstName", personRequest.getFirstName())
              .withString("lastName", personRequest.getLastName())
              .withNumber("age", personRequest.getAge())
              .withString("address", personRequest.getAddress())));
    }

    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.dynamoDb = new DynamoDB(client);
    }
}
