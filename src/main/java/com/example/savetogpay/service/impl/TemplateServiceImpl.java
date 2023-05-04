package com.example.savetogpay.service.impl;

import com.example.savetogpay.dto.CardClassAttributesDto;
import com.example.savetogpay.dto.GetTemplateDto;
import com.example.savetogpay.entity.TemplateEntity;
import com.example.savetogpay.exception.TemplateOrCardException;
import com.example.savetogpay.gpay.strategy.TemplateStrategy;
import com.example.savetogpay.gpay.strategy.strategychooser.TemplateStrategyChooser;
import com.example.savetogpay.repository.TemplateDao;
import com.example.savetogpay.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {
    private TemplateDao templateDao;
    private final TemplateStrategyChooser strategyChooser = new TemplateStrategyChooser();

    TemplateServiceImpl() {

    }

    @Autowired
    TemplateServiceImpl(TemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    @Override
    public String createTemplate(String apiKey, CardClassAttributesDto cardClassAttributes) {
        throwIfKeyNull(apiKey, "Api key");
        TemplateStrategy templateStrategy = strategyChooser.choose(cardClassAttributes.getCardType());
        String classId = templateStrategy.create(cardClassAttributes);
        templateDao.save(new TemplateEntity(apiKey, classId, cardClassAttributes.getCardType(), cardClassAttributes.getName()));
        return classId;
    }

    @Override
    public List<GetTemplateDto> getTemplates(String apiKey) {
        List<TemplateEntity> templateEntityList = templateDao.findAllByApiKey(apiKey);
        return templateEntityList.stream()
                .map(t -> new GetTemplateDto(t.getCardType(), t.getName(), t.getClassId()))
                .toList();
    }

    @Override
    public String updateTemplate(String classId, CardClassAttributesDto cardClassAttributes) {
        throwIfKeyNull(classId, "Class id");
        TemplateStrategy templateStrategy = strategyChooser.choose(cardClassAttributes.getCardType());
        return templateStrategy.update(classId, cardClassAttributes);
    }

    private void throwIfKeyNull(String key, String idType) {
        if (key == null || key.isBlank()) {
            String errorMessage = String.format("%s not specified.", idType);
            throw new TemplateOrCardException(errorMessage);
        }
    }
}
