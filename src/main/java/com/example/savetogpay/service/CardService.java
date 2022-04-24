package com.example.savetogpay.service;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.google.api.client.json.GenericJson;

import java.util.List;

public interface CardService {
    GetCardDto createCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception;
    List<GetCardDto> getCards(CardObjectAttributesDto cardObjectAttributesDto) throws Exception;
    GenericJson getCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception;
    GenericJson updateCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception;
}
