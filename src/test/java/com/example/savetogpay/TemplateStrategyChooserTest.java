package com.example.savetogpay;

import com.example.savetogpay.exception.TemplateOrCardException;
import com.example.savetogpay.gpay.strategy.TemplateStrategy;
import com.example.savetogpay.gpay.strategy.strategychooser.TemplateStrategyChooser;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TemplateStrategyChooserTest {
    @Mock
    private TemplateStrategy templateStrategyMock;

    private TemplateStrategyChooser templateStrategyChooser;

    public TemplateStrategyChooserTest() {
        MockitoAnnotations.openMocks(this);
        templateStrategyChooser = new TemplateStrategyChooser();
    }

    @Test
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

    @Test
    void choose_shouldThrowException_whenTypeIsIncorrect() {
        String type = "invalidType";

        assertThrows(TemplateOrCardException.class, () -> {
            templateStrategyChooser.choose(type);
        });
    }
}
