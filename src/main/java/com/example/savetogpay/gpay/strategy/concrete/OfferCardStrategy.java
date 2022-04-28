package com.example.savetogpay.gpay.strategy.concrete;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.example.savetogpay.gpay.Jwt;
import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.OfferObject;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

class OfferCardStrategy extends AbstractCardStrategy {
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

    @Override
    List<GetCardDto> doGetAll(String classId) throws Exception {
        GenericJson getCallResponse = restMethods.getAllOfferObjects(classId);
        return handleGetAllCallStatusCode(getCallResponse);
    }

    @Override
    GenericJson doGet(String objectId) {
        return restMethods.getOfferObject(objectId);
    }

    @Override
    GenericJson doUpdate(CardObjectAttributesDto cardObjectAttributes) {
        OfferObject objectResponse = resourceDefinitions.makeOfferObjectResource(
                cardObjectAttributes.getClassId(),
                cardObjectAttributes.getObjectId(),
                cardObjectAttributes
        );
        return restMethods.updateOfferObject(cardObjectAttributes.getObjectId(), objectResponse);
    }

    private List<GetCardDto> handleGetAllCallStatusCode(GenericJson getCallResponse) throws Exception {
        if (getCallResponse == null) {
            throw new Exception("Objects get issue.");
        }
        if ((int)getCallResponse.get("code") == 200) {
            OfferObject[] offerObjects = (OfferObject[]) getCallResponse.get("resources");
            return Arrays.stream(offerObjects)
                    .map(o -> new GetCardDto(o.getClassId(), o.getId()))
                    .toList();
        }
        throw new Exception("Object get issue." + getCallResponse.toPrettyString());
    }
}
