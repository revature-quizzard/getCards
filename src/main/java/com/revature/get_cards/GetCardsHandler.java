package com.revature.get_cards;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class GetCardsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Gson mapper = new GsonBuilder().setPrettyPrinting().create();
    private final CardRepo cardRepo = new CardRepo();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context){

        LambdaLogger logger = context.getLogger();

        logger.log("Received event: " + requestEvent);

        List<Card> cards = cardRepo.getAllCards();
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setBody(mapper.toJson(cards));

        return responseEvent;

    }
}
