package com.example.savetogpay.dto;

public class CardClassAttributesDto {
    // business logic information
    private String cardType;
    private String name;

    // Google Pay for Passes service information
    private String classId;

    // Google Pay for Passes card payload
    private String issuerName;
    private String provider;
    private String redemptionChannel;
    private String reviewStatus;
    private String title;
    private String imageUri;

    private String programName;

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

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getRedemptionChannel() {
        return redemptionChannel;
    }

    public void setRedemptionChannel(String redemptionChannel) {
        this.redemptionChannel = redemptionChannel;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
