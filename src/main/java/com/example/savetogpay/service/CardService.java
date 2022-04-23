package com.example.savetogpay.service;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;

public interface CardService {
    GetCardDto createCard(CardObjectAttributesDto cardObjectAttributesDto) throws Exception;
    void update(CardObjectAttributesDto cardObjectAttributesDto) throws Exception;
}
