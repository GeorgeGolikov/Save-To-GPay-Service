package com.example.savetogpay.controller;

import com.example.savetogpay.dto.*;
import com.example.savetogpay.service.CardService;
import com.example.savetogpay.service.TemplateService;
import com.google.api.client.json.GenericJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SaveToGpayController {
    private TemplateService templateService;
    private CardService cardService;

    SaveToGpayController() {

    }

    @Autowired
    SaveToGpayController(TemplateService templateService, CardService cardService) {
        this.templateService = templateService;
        this.cardService = cardService;
    }

    @PostMapping("/template")
    public ResponseEntity<Object> createTemplate(@RequestBody CreateTemplateRequestDto requestDto) {
        try {
            String classId = templateService.createTemplate(requestDto.getApiKey(), requestDto.getCardClassAttributesDto());
            return new ResponseEntity<>(classId, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/all-templates")
    public ResponseEntity<Object> getTemplates(@RequestParam String apiKey) {
        List<GetTemplateDto> templateDtos = templateService.getTemplates(apiKey);
        if (!templateDtos.isEmpty()) {
            return ResponseEntity.ok().body(templateDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/template")
    public ResponseEntity<Object> updateTemplate(@RequestBody CreateTemplateRequestDto requestDto) {
        CardClassAttributesDto cardClassAttributes = requestDto.getCardClassAttributesDto();
        try {
            String classId = templateService.updateTemplate(cardClassAttributes.getClassId(), cardClassAttributes);
            return ResponseEntity.ok(classId);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PostMapping("/card")
    public ResponseEntity<Object> createCard(@RequestBody CardObjectAttributesDto cardAttributesDto) {
        try {
            GetCardDto cardDto = cardService.createCard(cardAttributesDto);
            return new ResponseEntity<>(cardDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/all-cards")
    public ResponseEntity<Object> getCards(@RequestParam String classId, @RequestParam String type) {
        CardObjectAttributesDto cardAttributesDto = new CardObjectAttributesDto();
        cardAttributesDto.setClassId(classId);
        cardAttributesDto.setCardType(type);
        try {
            List<GetCardDto> getCardDtos = cardService.getCards(cardAttributesDto);
            return ResponseEntity.ok(getCardDtos);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/card")
    public ResponseEntity<Object> getCard(@RequestParam String objectId, @RequestParam String type) {
        CardObjectAttributesDto cardAttributesDto = new CardObjectAttributesDto();
        cardAttributesDto.setObjectId(objectId);
        cardAttributesDto.setCardType(type);
        try {
            GenericJson card = cardService.getCard(cardAttributesDto);
            return ResponseEntity.ok(card);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PatchMapping("/card")
    public ResponseEntity<Object> updateCard(@RequestBody CardObjectAttributesDto cardAttributesDto) {
        try {
            GenericJson card = cardService.updateCard(cardAttributesDto);
            return ResponseEntity.ok(card);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
