package com.example.savetogpay.controller;

import com.example.savetogpay.dto.*;
import com.example.savetogpay.service.TemplateService;
import com.example.savetogpay.service.CardService;
import com.google.api.client.json.GenericJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<?> createTemplate(@RequestBody CreateTemplateRequestDto requestDto) {
        try {
            String classId = templateService.createTemplate(requestDto.getApiKey(), requestDto.getCardClassAttributesDto());
            return ResponseEntity.created(URI.create("/all-templates?apiKey=<your_api_key>")).body(classId);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/all-templates")
    public ResponseEntity<?> getTemplates(@RequestParam String apiKey) {
        List<GetTemplateDto> templateDtos = templateService.getTemplates(apiKey);
        if (!templateDtos.isEmpty()) {
            return ResponseEntity.ok().body(templateDtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/template")
    public ResponseEntity<?> updateTemplate(@RequestBody CreateTemplateRequestDto requestDto) {
        CardClassAttributesDto cardClassAttributes = requestDto.getCardClassAttributesDto();
        try {
            String classId = templateService.updateTemplate(cardClassAttributes.getClassId(), cardClassAttributes);
            return ResponseEntity.ok(classId);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PostMapping("/card")
    public ResponseEntity<?> createCard(@RequestBody CardObjectAttributesDto cardAttributesDto) {
        try {
            GetCardDto cardDto = cardService.createCard(cardAttributesDto);
            return ResponseEntity.created(
                URI.create(
                    String.format("/card?objectId=%s", cardDto.getObjectId())
                )
            ).body(cardDto);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/all-cards")
    public ResponseEntity<?> getCards(@RequestParam CardObjectAttributesDto cardAttributesDto) {
        try {
            List<GetCardDto> getCardDtos = cardService.getCards(cardAttributesDto);
            return ResponseEntity.ok(getCardDtos);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/card")
    public ResponseEntity<?> getCard(@RequestParam CardObjectAttributesDto cardAttributesDto) {
        try {
            GenericJson card = cardService.getCard(cardAttributesDto);
            return ResponseEntity.ok(card);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PatchMapping("/card")
    public ResponseEntity<?> updateCard(@RequestBody CardObjectAttributesDto cardAttributesDto) {
        try {
            GenericJson card = cardService.updateCard(cardAttributesDto);
            return ResponseEntity.ok(card);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
