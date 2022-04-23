package com.example.savetogpay.gpay.strategy.concrete;

import com.example.savetogpay.dto.CardClassAttributesDto;
import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.LoyaltyClass;

public class LoyaltyTemplateStrategy extends AbstractTemplateStrategy {
    @Override
    GenericJson doCreate(String classId, CardClassAttributesDto cardClassAttributes) {
        LoyaltyClass classResourcePayload = resourceDefinitions.makeLoyaltyClassResource(classId, cardClassAttributes);
        return restMethods.insertLoyaltyClass(classResourcePayload);
    }

    @Override
    GenericJson doUpdate(String classId, CardClassAttributesDto cardClassAttributes) {
        LoyaltyClass classResourcePayload = resourceDefinitions.makeLoyaltyClassResource(classId, cardClassAttributes);
        return restMethods.updateLoyaltyClass(classId, classResourcePayload);
    }
}
