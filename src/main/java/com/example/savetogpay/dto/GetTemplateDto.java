package com.example.savetogpay.dto;

public class GetTemplateDto {
    // business logic information
    private String cardType;
    private String name;

    // Google Pay for Passes service information
    private String classId;

    public GetTemplateDto(String cardType, String name, String classId) {
        this.cardType = cardType;
        this.name = name;
        this.classId = classId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
