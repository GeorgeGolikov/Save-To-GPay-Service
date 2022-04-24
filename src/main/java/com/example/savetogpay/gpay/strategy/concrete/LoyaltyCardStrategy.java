package com.example.savetogpay.gpay.strategy.concrete;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.example.savetogpay.gpay.Jwt;
import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.LoyaltyObject;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class LoyaltyCardStrategy extends AbstractCardStrategy{
    @Override
    GenericJson doCreate(CardObjectAttributesDto cardObjectAttributes) {
        LoyaltyObject objectResponse = resourceDefinitions.makeLoyaltyObjectResource(
                cardObjectAttributes.getClassId(),
                cardObjectAttributes.getObjectId(),
                cardObjectAttributes
        );
        return restMethods.insertLoyaltyObject(objectResponse);
    }

    @Override
    void addObjectToGooglePassJwt(Jwt googlePassJwt, JsonObject jwtPayload) {
        googlePassJwt.addLoyaltyObject(jwtPayload);
    }

    @Override
    List<GetCardDto> doGetAll(String classId) throws Exception {
        GenericJson getCallResponse = restMethods.getAllLoyaltyObjects(classId);
        return handleGetAllCallStatusCode(getCallResponse);
    }

    @Override
    GenericJson doGet(String objectId) {
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

    private List<GetCardDto> handleGetAllCallStatusCode(GenericJson getCallResponse) throws Exception {
        if (getCallResponse == null) {
            throw new Exception("Objects get issue.");
        }
        if ((int)getCallResponse.get("code") == 200) {
            LoyaltyObject[] loyaltyObjects = (LoyaltyObject[]) getCallResponse.get("resources");
            return Arrays.stream(loyaltyObjects)
                    .map(o -> new GetCardDto(o.getClassId(), o.getId()))
                    .toList();
        }
        throw new Exception("Object get issue." + getCallResponse.toPrettyString());
    }
}
