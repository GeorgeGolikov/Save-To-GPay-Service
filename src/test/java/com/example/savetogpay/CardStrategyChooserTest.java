package com.example.savetogpay;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.savetogpay.exception.TemplateOrCardException;
import com.example.savetogpay.gpay.ResourceDefinitions;
import com.example.savetogpay.gpay.RestMethods;
import com.example.savetogpay.gpay.strategy.CardStrategy;
import com.example.savetogpay.gpay.strategy.concrete.LoyaltyCardStrategy;
import com.example.savetogpay.gpay.strategy.concrete.OfferCardStrategy;
import com.example.savetogpay.gpay.strategy.strategychooser.CardStrategyChooser;
import com.example.savetogpay.gpay.strategy.strategychooser.TemplateStrategyChooser;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CardStrategyChooserTest {

    @Mock
    private CardStrategy cardStrategyMock;

    private CardStrategyChooser cardStrategyChooser;

    public CardStrategyChooserTest() {
        MockitoAnnotations.openMocks(this);
        cardStrategyChooser = new CardStrategyChooser();
    }

    @org.junit.jupiter.api.Test
    void choose_shouldReturnTemplateStrategy_whenTypeIsCorrect() {
//        String type = "loyalty";
//        String className = String.format("com.example.savetogpay.gpay.strategy.concrete.%sTemplateStrategy", type);
//        when(templateStrategyMock.getClass().getName()).thenReturn(className);
//
//        TemplateStrategy chosenStrategy = templateStrategyChooser.choose(type);
//
//        assertNotNull(chosenStrategy);
//        assertEquals(templateStrategyMock.getClass(), chosenStrategy.getClass());
//        verify(templateStrategyMock, times(1)).getClass();
    }

    @org.junit.jupiter.api.Test
    void choose_shouldThrowException_whenTypeIsIncorrect() {
        String type = "invalidType";

        assertThrows(TemplateOrCardException.class, () -> {
            cardStrategyChooser.choose(type);
        });
    }

    @Test
    public void testChooseLoyalty() {
        CardStrategy strategy = cardStrategyChooser.choose("Loyalty");
        assertTrue(strategy instanceof LoyaltyCardStrategy);
    }

    @Test
    public void testChooseOffer() {
        CardStrategy strategy = cardStrategyChooser.choose("Offer");
        assertTrue(strategy instanceof OfferCardStrategy);
    }
}
