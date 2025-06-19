package com.example.yogasehoga.All_Yoga_Info;

public class YogaPosem {
    private String yogaName;
    private String imageUrl;

    public YogaPosem() {
        // Needed for Firestore
    }

    public YogaPosem(String yogaName, String imageUrl) {
        this.yogaName = yogaName;
        this.imageUrl = imageUrl;
    }

    public String getYogaName() {
        return yogaName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
