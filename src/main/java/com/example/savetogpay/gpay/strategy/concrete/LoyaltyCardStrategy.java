package com.example.savetogpay.gpay.strategy.concrete;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.example.savetogpay.exception.TemplateOrCardException;
import com.example.savetogpay.gpay.Jwt;
import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.LoyaltyObject;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoyaltyCardStrategy extends AbstractCardStrategy{
    @Override
    public GenericJson doCreate(CardObjectAttributesDto cardObjectAttributes) {
        LoyaltyObject objectResponse = resourceDefinitions.makeLoyaltyObjectResource(
                cardObjectAttributes.getClassId(),
                cardObjectAttributes.getObjectId(),
                cardObjectAttributes
        );
        return restMethods.insertLoyaltyObject(objectResponse);
    }

    @Override
    public void addObjectToGooglePassJwt(Jwt googlePassJwt, JsonObject jwtPayload) {
        googlePassJwt.addLoyaltyObject(jwtPayload);
    }

    @Override
    public List<GetCardDto> doGetAll(String classId) {
        GenericJson getCallResponse = restMethods.getAllLoyaltyObjects(classId);
        return handleGetAllCallStatusCode(getCallResponse);
    }

    @Override
    public GenericJson doGet(String objectId) {
        return restMethods.getLoyaltyObject(objectId);
    }

    @Override
    GenericJson doUpdate(CardObjectAttributesDto cardObjectAttributes) {
        LoyaltyObject objectResponse = resourceDefinitions.makeLoyaltyObjectResource(
                cardObjectAttributes.getClassId(),
                cardObjectAttributes.getObjectId(),
                cardObjectAttributes
        );
        return restMethods.updateLoyaltyObject(cardObjectAttributes.getObjectId(), objectResponse);
    }

    private List<GetCardDto> handleGetAllCallStatusCode(GenericJson getCallResponse) {
        if (getCallResponse == null) {
            throw new TemplateOrCardException("Objects get issue.");
        }
        if ((int)getCallResponse.get("code") == 200) {
            LoyaltyObject[] loyaltyObjects = (LoyaltyObject[]) getCallResponse.get("resources");
            return Arrays.stream(loyaltyObjects)
                    .map(o -> new GetCardDto(o.getClassId(), o.getId()))
                    .toList();
        }

        String responseAsStr;
        try {
            responseAsStr = getCallResponse.toPrettyString();
        } catch (IOException e) {
            throw new TemplateOrCardException("Object get issue. Response cannot be stringified.");
        }
        throw new TemplateOrCardException("Object get issue." + responseAsStr);
    }
}
