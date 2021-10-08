package com.revature.get_cards;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.revature.exception.ResourceNotFoundException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The SetRepo class is a database repository which provides CRUD operations on the Sets table.
 */

public class SetRepo {

    private final DynamoDBMapper dbReader;

    public SetRepo(){ dbReader = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient()); }

    public Set getSetId(String id){

        Map<String, AttributeValue> input = new HashMap<>();
        input.put(":id", new AttributeValue().withS(id));

        DynamoDBScanExpression query = new DynamoDBScanExpression()
                .withFilterExpression("id = :id")
                .withExpressionAttributeValues(input);

        List<Set> result = dbReader.scan(Set.class, query);

        if(result.size() == 0) {
            throw new ResourceNotFoundException();
        }

        return result.get(0);
    }
}
