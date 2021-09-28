package com.revature.get_cards;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CardRepo {

    private final DynamoDBMapper dbReader;

    public CardRepo() { dbReader = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient()); }

    public List<Card> getAllCards(){

        Map<String, AttributeValue> inputs = new HashMap<>();

        DynamoDBScanExpression query = new DynamoDBScanExpression()
                .withExpressionAttributeValues(inputs);

        List<Card> queryResult = dbReader.scan(Card.class, query);

        return queryResult;
    }

}
