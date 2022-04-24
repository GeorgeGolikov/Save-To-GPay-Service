package com.example.savetogpay.gpay.strategy;

import com.example.savetogpay.dto.CardObjectAttributesDto;
import com.example.savetogpay.dto.GetCardDto;
import com.google.api.client.json.GenericJson;

import java.util.List;

public interface CardStrategy {
    GetCardDto create(CardObjectAttributesDto cardObjectAttributes) throws Exception;
    List<GetCardDto> getAll(String classId) throws Exception;
    GenericJson get(String objectId) throws Exception;
    GenericJson update(CardObjectAttributesDto cardObjectAttributes) throws Exception;
}
