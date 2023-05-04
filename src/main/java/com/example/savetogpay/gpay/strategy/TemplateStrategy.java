package com.example.savetogpay.gpay.strategy;

import com.example.savetogpay.dto.CardClassAttributesDto;

public interface TemplateStrategy {
    String create(CardClassAttributesDto cardClassAttributes);
    String update(String classId, CardClassAttributesDto cardClassAttributes);
}
