package com.example.savetogpay.service.impl;

import com.example.savetogpay.dto.CardClassAttributesDto;
import com.example.savetogpay.dto.GetTemplateDto;
import com.example.savetogpay.entity.TemplateEntity;
import com.example.savetogpay.gpay.strategy.TemplateStrategy;
import com.example.savetogpay.gpay.strategy.concrete.TemplateStrategyChooser;
import com.example.savetogpay.repository.TemplateDao;
import com.example.savetogpay.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    private TemplateDao templateDao;
    private TemplateStrategy templateStrategy;

    TemplateServiceImpl() {

    }

    @Autowired
    TemplateServiceImpl(TemplateDao templateDao) {
        this.templateDao = templateDao;
    }

    @Override
    public String createTemplate(String apiKey, CardClassAttributesDto cardClassAttributes) throws Exception {
        if (apiKey == null || apiKey.equals("")) {
            throw new Exception("Api key not specified.");
        }
        templateStrategy = TemplateStrategyChooser.choose(cardClassAttributes.getCardType());
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
    public String updateTemplate(String classId, CardClassAttributesDto cardClassAttributes) throws Exception {
        if (classId == null || classId.equals("")) {
            throw new Exception("Class id not specified.");
        }
        templateStrategy = TemplateStrategyChooser.choose(cardClassAttributes.getCardType());
        return templateStrategy.update(classId, cardClassAttributes);
    }
}
