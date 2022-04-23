package com.example.savetogpay.dto;

public class CreateTemplateRequestDto {
    private String apiKey;
    private CardClassAttributesDto cardClassAttributesDto;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public CardClassAttributesDto getCardClassAttributesDto() {
        return cardClassAttributesDto;
    }

    public void setCardClassAttributesDto(CardClassAttributesDto cardClassAttributesDto) {
        this.cardClassAttributesDto = cardClassAttributesDto;
    }
}
