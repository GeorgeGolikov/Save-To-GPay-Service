package com.example.savetogpay.dto;

public class GetCardDto {
    // Google Pay for Passes service information
    private String classId;
    private String objectId;
    private String link;

    public GetCardDto() {}

    public GetCardDto(String classId, String objectId) {
        this.classId = classId;
        this.objectId = objectId;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
