package com.example.savetogpay.gpay.strategy.concrete;

import com.example.savetogpay.dto.CardClassAttributesDto;
import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.OfferClass;

class OfferTemplateStrategy extends AbstractTemplateStrategy {
    @Override
    public GenericJson doCreate(String classId, CardClassAttributesDto cardClassAttributes) {
        OfferClass classResourcePayload = resourceDefinitions.makeOfferClassResource(classId, cardClassAttributes);
        return restMethods.insertOfferClass(classResourcePayload);
    }

    @Override
    GenericJson doUpdate(String classId, CardClassAttributesDto cardClassAttributes) {
        OfferClass classResourcePayload = resourceDefinitions.makeOfferClassResource(classId, cardClassAttributes);
        return restMethods.updateOfferClass(classId, classResourcePayload);
    }
}
