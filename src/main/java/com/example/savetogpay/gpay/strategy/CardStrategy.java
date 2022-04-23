package com.example.savetogpay.gpay.strategy;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;

public interface CardStrategy {
    GetCardDto create(CardObjectAttributesDto cardObjectAttributes) throws Exception;
}
