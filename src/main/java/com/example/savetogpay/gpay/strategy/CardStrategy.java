package com.example.savetogpay.gpay.strategy;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.google.api.client.json.GenericJson;

import java.util.List;

public interface CardStrategy {
    GetCardDto create(CardObjectAttributesDto cardObjectAttributes);
    List<GetCardDto> getAll(String classId);
    GenericJson get(String objectId);
    GenericJson update(CardObjectAttributesDto cardObjectAttributes);
}
