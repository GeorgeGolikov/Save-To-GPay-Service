package com.example.savetogpay.gpay.strategy.concrete;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.gpay.Jwt;
import com.google.api.client.json.GenericJson;
import com.google.gson.JsonObject;

public class LoyaltyCardStrategy extends AbstractCardStrategy{
    @Override
    GenericJson doCreate(CardObjectAttributesDto cardObjectAttributes) {
        return null;
    }

    @Override
    void addObjectToGooglePassJwt(Jwt googlePassJwt, JsonObject jwtPayload) {
        googlePassJwt.addLoyaltyObject(jwtPayload);
    }
}
