package com.example.savetogpay.entity;

import javax.persistence.*;

@Entity(name = "template")
public class TemplateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String apiKey;
    private String classId;
    private String cardType;
    private String name;

    public TemplateEntity() {

    }

    public TemplateEntity(String apiKey, String classId, String cardType, String name) {
        this.apiKey = apiKey;
        this.classId = classId;
        this.cardType = cardType;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
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
}
