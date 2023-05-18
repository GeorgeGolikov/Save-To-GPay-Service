package com.example.savetogpay;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.example.savetogpay.exception.TemplateOrCardException;
import com.example.savetogpay.gpay.strategy.CardStrategy;
import com.example.savetogpay.gpay.strategy.strategychooser.CardStrategyChooser;
import com.example.savetogpay.service.impl.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.google.api.client.json.GenericJson;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {
    @Mock
    private CardStrategyChooser strategyChooser;
    @InjectMocks
    private CardServiceImpl cardService;

    @Test
    void createCard_shouldThrowException_whenClassIdIsNull() {
        CardObjectAttributesDto dto = new CardObjectAttributesDto();
        dto.setCardType("type");

        assertThrows(TemplateOrCardException.class, () -> cardService.createCard(dto));
    }

    @Test
    void getCards_shouldThrowException_whenClassIdIsNull() {
        CardObjectAttributesDto dto = new CardObjectAttributesDto();
        dto.setCardType("type");

        assertThrows(TemplateOrCardException.class, () -> cardService.getCards(dto));
    }

    @Test
    void getCard_shouldThrowException_whenObjectIdIsNull() {
        CardObjectAttributesDto dto = new CardObjectAttributesDto();
        dto.setCardType("type");

        assertThrows(TemplateOrCardException.class, () -> cardService.getCard(dto));
    }

    @Test
    void updateCard_shouldThrowException_whenObjectIdIsNull() {
        CardObjectAttributesDto dto = new CardObjectAttributesDto();
        dto.setCardType("type");

        assertThrows(TemplateOrCardException.class, () -> cardService.updateCard(dto));
    }

    @Test
    void createCard_shouldCallStrategyChooserAndReturnDto() {
//        CardObjectAttributesDto dto = new CardObjectAttributesDto();
//        dto.setClassId("classId");
//        dto.setCardType("type");
//
//        CardStrategy cardStrategy = mock(CardStrategy.class);
//        GetCardDto getCardDto = new GetCardDto();
//
//        when(strategyChooser.choose(dto.getCardType())).thenReturn(cardStrategy);
//        when(cardStrategy.create(dto)).thenReturn(getCardDto);
//
//        GetCardDto result = cardService.createCard(dto);
//
//        verify(strategyChooser, times(1)).choose(dto.getCardType());
//        verify(cardStrategy, times(1)).create(dto);
//        assertEquals(getCardDto, result);
    }

    @Test
    void getCards_shouldCallStrategyChooserAndReturnList() {
//        CardObjectAttributesDto dto = new CardObjectAttributesDto();
//        dto.setClassId("classId");
//        dto.setCardType("type");
//
//        CardStrategy cardStrategy = mock(CardStrategy.class);
//        List<GetCardDto> getCardDtos = Collections.singletonList(new GetCardDto());
//
//        when(strategyChooser.choose(dto.getCardType())).thenReturn(cardStrategy);
//        when(cardStrategy.getAll(dto.getClassId())).thenReturn(getCardDtos);
//
//        List<GetCardDto> result = cardService.getCards(dto);
//
//        verify(strategyChooser, times(1)).choose(dto.getCardType());
//        verify(cardStrategy, times(1)).getAll(dto.getClassId());
//        assertEquals(getCardDtos, result);
    }

}
