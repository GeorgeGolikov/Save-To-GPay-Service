package com.example.savetogpay;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.example.savetogpay.gpay.ResourceDefinitions;
import com.example.savetogpay.gpay.RestMethods;
import com.example.savetogpay.gpay.strategy.concrete.LoyaltyCardStrategy;
import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.LoyaltyObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoyaltyCardStrategyTest {
    @Mock
    private ResourceDefinitions resourceDefinitions;

    @Mock
    private RestMethods restMethods;

    @InjectMocks
    private LoyaltyCardStrategy loyaltyCardStrategy;

    private CardObjectAttributesDto cardObjectAttributesDto;

    @BeforeEach
    void setUp() {
        cardObjectAttributesDto = new CardObjectAttributesDto();
        cardObjectAttributesDto.setClassId("classId");
        cardObjectAttributesDto.setObjectId("objectId");
    }

    @Test
    void testDoCreate() {
        LoyaltyObject loyaltyObject = new LoyaltyObject();
        when(resourceDefinitions.makeLoyaltyObjectResource(
                cardObjectAttributesDto.getClassId(),
                cardObjectAttributesDto.getObjectId(),
                cardObjectAttributesDto
        )).thenReturn(loyaltyObject);

        GenericJson expectedResponse = new GenericJson();
        when(restMethods.insertLoyaltyObject(loyaltyObject)).thenReturn(expectedResponse);

        GenericJson actualResponse = loyaltyCardStrategy.doCreate(cardObjectAttributesDto);
        assertEquals(expectedResponse, actualResponse);

        verify(resourceDefinitions).makeLoyaltyObjectResource(
                cardObjectAttributesDto.getClassId(),
                cardObjectAttributesDto.getObjectId(),
                cardObjectAttributesDto
        );
        verify(restMethods).insertLoyaltyObject(loyaltyObject);
    }

    @Test
    void testDoGetAll() {
//        String classId = "classId";
//        GenericJson getCallResponse = new GenericJson();
//        LoyaltyObject[] loyaltyObjects = new LoyaltyObject[2];
//        loyaltyObjects[0] = new LoyaltyObject();
//        loyaltyObjects[1] = new LoyaltyObject();
//        getCallResponse.set("code", 200);
//        getCallResponse.set("resources", loyaltyObjects);
//
//        when(restMethods.getAllLoyaltyObjects(classId)).thenReturn(getCallResponse);
//
//        List<GetCardDto> expectedResponse = Arrays.asList(
//                new GetCardDto(loyaltyObjects[0].getClassId(), loyaltyObjects[0].getId()),
//                new GetCardDto(loyaltyObjects[1].getClassId(), loyaltyObjects[1].getId())
//        );
//
//        List<GetCardDto> actualResponse = loyaltyCardStrategy.doGetAll(classId);
//        assertEquals(expectedResponse, actualResponse);
//
//        verify(restMethods).getAllLoyaltyObjects(classId);
    }

    @Test
    void testDoGet() {
        String objectId = "objectId";
        GenericJson expectedResponse = new GenericJson();

        when(restMethods.getLoyaltyObject(objectId)).thenReturn(expectedResponse);

        GenericJson actualResponse = loyaltyCardStrategy.doGet(objectId);
        assertEquals(expectedResponse, actualResponse);

        verify(restMethods).getLoyaltyObject(objectId);
    }

}