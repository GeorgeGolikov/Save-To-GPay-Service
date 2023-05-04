package com.example.savetogpay.gpay.strategy.concrete;

import com.example.savetogpay.dto.CardClassAttributesDto;
import com.example.savetogpay.exception.TemplateOrCardException;
import com.example.savetogpay.gpay.Config;
import com.example.savetogpay.gpay.ResourceDefinitions;
import com.example.savetogpay.gpay.RestMethods;
import com.example.savetogpay.gpay.strategy.TemplateStrategy;
import com.google.api.client.json.GenericJson;

import java.io.IOException;
import java.util.UUID;

abstract class AbstractTemplateStrategy implements TemplateStrategy {
    protected Config config = Config.getInstance();
    protected ResourceDefinitions resourceDefinitions = ResourceDefinitions.getInstance();
    protected RestMethods restMethods = RestMethods.getInstance();

    @Override
    public String create(CardClassAttributesDto cardClassAttributes) {
        String classId = cardClassAttributes.getClassId();
        if (classId == null) {
            // your classUid should be a hash based off of pass metadata, for the demo we will use pass-type_class_uniqueid
            String classUid = String.format(
                    "%s_CLASS_%s",
                    cardClassAttributes.getCardType().toUpperCase(),
                    UUID.randomUUID().toString()
            );
            // check Reference API for format of "id", for example offer: (https://developers.google.com/pay/passes/reference/v1/offerclass/insert).
            // must be alphanumeric characters, ".", "_", or "-".
            classId = String.format("%s.%s" , config.getIssuerId(), classUid);
        }

        GenericJson classResponse = doCreate(classId, cardClassAttributes);
        return handleInsertCallStatusCode(classResponse, classId);
    }

    abstract GenericJson doCreate(String classId, CardClassAttributesDto cardClassAttributes);

    @Override
    public String update(String classId, CardClassAttributesDto cardClassAttributes) {
        GenericJson classResponse = doUpdate(classId, cardClassAttributes);
        return handleInsertCallStatusCode(classResponse, classId);
    }

    abstract GenericJson doUpdate(String classId, CardClassAttributesDto cardClassAttributes);

    private String handleInsertCallStatusCode(GenericJson insertCallResponse, String classId) {
        if (insertCallResponse == null) {
            throw new TemplateOrCardException("Class insert issue.");
        }
        if ((int)insertCallResponse.get("code") == 200) {
            return classId;
        }
        if ((int)insertCallResponse.get("code") == 409) {  // Id resource exists for this issuer account
            throw new TemplateOrCardException(String.format("ClassId: (%s) already exists.", classId));
        }

        String responseAsStr;
        try {
            responseAsStr = insertCallResponse.toPrettyString();
        } catch (IOException e) {
            throw new TemplateOrCardException("Class insert issue. Response cannot be stringified.");
        }
        throw new TemplateOrCardException("Class insert issue." + responseAsStr);
    }
}
