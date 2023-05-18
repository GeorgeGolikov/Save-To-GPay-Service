package com.example.savetogpay;

import com.example.savetogpay.dto.CardClassAttributesDto;
import com.example.savetogpay.dto.GetTemplateDto;
import com.example.savetogpay.entity.TemplateEntity;
import com.example.savetogpay.exception.TemplateOrCardException;
import com.example.savetogpay.repository.TemplateDao;
import com.example.savetogpay.service.TemplateService;
import com.example.savetogpay.service.impl.TemplateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateServiceImplTest {

    @Mock
    private TemplateDao templateDao;

    private TemplateService templateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        templateService = new TemplateServiceImpl(templateDao);
    }

    @Test
    public void testCreateTemplate() {
//        CardClassAttributesDto attributes = new CardClassAttributesDto();
//        when(templateDao.save(any())).thenReturn(new TemplateEntity("apiKey", "classId", "cardType", "name"));
//        String classId = templateService.createTemplate("apiKey", attributes);
//        assertEquals("classId", classId);
    }

    @Test
    public void testGetTemplates() {
        when(templateDao.findAllByApiKey("apiKey")).thenReturn(new ArrayList<>());
        List<GetTemplateDto> templates = templateService.getTemplates("apiKey");
        assertNotNull(templates);
        assertEquals(0, templates.size());
    }

    @Test
    public void testUpdateTemplate() {
//        CardClassAttributesDto attributes = new CardClassAttributesDto();
//        when(templateDao.save(any())).thenReturn(new TemplateEntity("apiKey", "classId", "cardType", "name"));
//        String classId = templateService.createTemplate("apiKey", attributes);
//        when(templateDao.findById(Long.valueOf(classId))).thenReturn(Optional.of(new TemplateEntity("apiKey", "classId", "cardType", "name")));
//        String newClassId = templateService.updateTemplate(classId, attributes);
//        assertEquals(classId, newClassId);
    }

    @Test
    public void testThrowIfKeyNull() {
        assertThrows(TemplateOrCardException.class, () -> {
            templateService.createTemplate(null, new CardClassAttributesDto());
        });
    }
}
