package com.revature.get_cards;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;



/**
 * The GetCardsHandler class is an AWS Lambda function to handle get operations on Card objects.
 * This function occupies the GET Request Method on the Card endpoint.
 */


public class GetCardsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Gson mapper = new GsonBuilder().setPrettyPrinting().create();
    private final SetRepo setRepo = new SetRepo();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context){

        LambdaLogger logger = context.getLogger();

        logger.log("Received event: " + requestEvent);

        String id = requestEvent.getQueryStringParameters().get("id");

        Set sets = setRepo.getSetId(id);
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setBody(mapper.toJson(sets.getCards()));

        System.out.println(responseEvent);
        return responseEvent;

    }
}
