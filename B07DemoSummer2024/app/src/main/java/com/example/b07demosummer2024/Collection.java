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

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public String getPeriod() {
        return period;
    }
}
