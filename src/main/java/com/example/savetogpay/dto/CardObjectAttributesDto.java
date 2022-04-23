package com.example.savetogpay.dto;

public class CardObjectAttributesDto {
    // business logic information
    private String name;
    private String cardType;

    // Google Pay for Passes service information
    private String classId;
    private String objectId;

    // Google Pay for Passes card payload
    private String state;
    private String barcodeType;
    private String barcodeValue;
    private String barcodeAlternateText;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBarcodeType() {
        return barcodeType;
    }

    public void setBarcodeType(String barcodeType) {
        this.barcodeType = barcodeType;
    }

    public String getBarcodeValue() {
        return barcodeValue;
    }

    public void setBarcodeValue(String barcodeValue) {
        this.barcodeValue = barcodeValue;
    }

    public String getBarcodeAlternateText() {
        return barcodeAlternateText;
    }

    public void setBarcodeAlternateText(String barcodeAlternateText) {
        this.barcodeAlternateText = barcodeAlternateText;
    }
}
