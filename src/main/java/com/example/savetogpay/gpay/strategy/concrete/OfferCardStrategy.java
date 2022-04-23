package com.example.savetogpay.gpay.strategy.concrete;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.gpay.Jwt;
import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.OfferObject;
import com.google.gson.JsonObject;

public class OfferCardStrategy extends AbstractCardStrategy {
    @Override
    GenericJson doCreate(CardObjectAttributesDto cardObjectAttributes) {
        OfferObject objectResponse = resourceDefinitions.makeOfferObjectResource(
                cardObjectAttributes.getClassId(),
                cardObjectAttributes.getObjectId(),
                cardObjectAttributes
        );
        return restMethods.insertOfferObject(objectResponse);
    }

    @Override
    void addObjectToGooglePassJwt(Jwt googlePassJwt, JsonObject jwtPayload) {
        googlePassJwt.addOfferObject(jwtPayload);
    }
}
