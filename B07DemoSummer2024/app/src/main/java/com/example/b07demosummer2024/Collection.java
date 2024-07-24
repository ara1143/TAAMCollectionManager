package com.example.b07demosummer2024;

public class Collection {
    public String name;
    public String lotNumber;
    public String category;
    public String period;
    public String description;
    public String mediaUrl;

    public Collection() {
    }

    public Collection(String name, String lotNumber, String category, String period, String description, String mediaUrl) {
        this.name = name;
        this.lotNumber = lotNumber;
        this.category = category;
        this.period = period;
        this.description = description;
        this.mediaUrl = mediaUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
