package com.example.savetogpay.service;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.google.api.client.json.GenericJson;

import java.util.List;

public interface CardService {
    GetCardDto createCard(CardObjectAttributesDto cardObjectAttributesDto);
    List<GetCardDto> getCards(CardObjectAttributesDto cardObjectAttributesDto);
    GenericJson getCard(CardObjectAttributesDto cardObjectAttributesDto);
    GenericJson updateCard(CardObjectAttributesDto cardObjectAttributesDto);
}
