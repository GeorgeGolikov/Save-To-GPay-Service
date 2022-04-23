package com.example.savetogpay.service;

import com.example.savetogpay.dto.CardClassAttributesDto;
import com.example.savetogpay.dto.GetTemplateDto;

import java.util.List;

public interface TemplateService {
    String createTemplate(String apiKey, CardClassAttributesDto cardClassAttributes) throws Exception;
    List<GetTemplateDto> getTemplates(String apiKey);
    String updateTemplate(String classId, CardClassAttributesDto cardClassAttributes) throws Exception;
}
