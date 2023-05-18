package com.example.savetogpay;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.example.savetogpay.gpay.ResourceDefinitions;
import com.example.savetogpay.gpay.RestMethods;
import com.example.savetogpay.gpay.strategy.concrete.OfferCardStrategy;
import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.OfferObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OfferCardStrategyTest {

    @Mock
    private ResourceDefinitions resourceDefinitions;

    @Mock
    private RestMethods restMethods;

    @InjectMocks
    private OfferCardStrategy offerCardStrategy;

    @Test
    void testDoCreate() {
//        CardObjectAttributesDto cardObjectAttributes = new CardObjectAttributesDto();
//        OfferObject objectResponse = new OfferObject();
//        GenericJson insertResponse = new GenericJson();
//        when(resourceDefinitions.makeOfferObjectResource(anyString(), anyString(), any(CardObjectAttributesDto.class)))
//                .thenReturn(objectResponse);
//        when(restMethods.insertOfferObject(objectResponse)).thenReturn(insertResponse);
//
//        GenericJson result = offerCardStrategy.doCreate(cardObjectAttributes);
//
//        verify(resourceDefinitions, times(1)).makeOfferObjectResource(anyString(), anyString(), eq(cardObjectAttributes));
//        verify(restMethods, times(1)).insertOfferObject(objectResponse);
//        assertSame(insertResponse, result);
    }

    @Test
    void testDoGetAll() {
//        String classId = "class123";
//        GenericJson getCallResponse = new GenericJson();
//        OfferObject[] offerObjects = new OfferObject[2];
//        offerObjects[0] = new OfferObject();
//        offerObjects[0].setClassId(classId);
//        offerObjects[0].setId("object1");
//        offerObjects[1] = new OfferObject();
//        offerObjects[1].setClassId(classId);
//        offerObjects[1].setId("object2");
//        getCallResponse.set("code", 200);
//        getCallResponse.set("resources", offerObjects);
//        when(restMethods.getAllOfferObjects(classId)).thenReturn(getCallResponse);
//
//        List<GetCardDto> result = offerCardStrategy.doGetAll(classId);
//
//        verify(restMethods, times(1)).getAllOfferObjects(classId);
//        assertIterableEquals(Arrays.asList(
//                new GetCardDto(classId, "object1"),
//                new GetCardDto(classId, "object2")), result);
    }

}