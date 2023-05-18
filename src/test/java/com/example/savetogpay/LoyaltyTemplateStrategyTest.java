package com.example.savetogpay;

import com.example.savetogpay.dto.CardClassAttributesDto;
import com.example.savetogpay.gpay.ResourceDefinitions;
import com.example.savetogpay.gpay.RestMethods;
import com.example.savetogpay.gpay.strategy.concrete.LoyaltyTemplateStrategy;
import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.model.OfferClass;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoyaltyTemplateStrategyTest {
    @Mock
    private ResourceDefinitions resourceDefinitions;

    @Mock
    private RestMethods restMethods;

    @InjectMocks
    private LoyaltyTemplateStrategy loyaltyTemplateStrategy;

    public LoyaltyTemplateStrategyTest() {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.jupiter.api.Test
    void testDoCreate() {
//        // Arrange
//        String classId = "123";
//        CardClassAttributesDto cardClassAttributes = new CardClassAttributesDto();
//        OfferClass classResourcePayload = new OfferClass();
//        when(resourceDefinitions.makeOfferClassResource(classId, cardClassAttributes)).thenReturn(classResourcePayload);
//        GenericJson expectedResponse = new GenericJson();
//        when(restMethods.insertOfferClass(classResourcePayload)).thenReturn(expectedResponse);
//
//        // Act
//        GenericJson actualResponse = loyaltyTemplateStrategy.doCreate(classId, cardClassAttributes);
//
//        // Assert
//        assertEquals(expectedResponse, actualResponse);
    }

    @org.junit.jupiter.api.Test
    void testDoUpdate() {
//        // Arrange
//        String classId = "123";
//        CardClassAttributesDto cardClassAttributes = new CardClassAttributesDto();
//        OfferClass classResourcePayload = new OfferClass();
//        when(resourceDefinitions.makeOfferClassResource(classId, cardClassAttributes)).thenReturn(classResourcePayload);
//        GenericJson expectedResponse = new GenericJson();
//        when(restMethods.updateOfferClass(classId, classResourcePayload)).thenReturn(expectedResponse);
//
//        // Act
//        GenericJson actualResponse = loyaltyTemplateStrategy.doUpdate(classId, cardClassAttributes);
//
//        // Assert
//        assertEquals(expectedResponse, actualResponse);
    }
}
