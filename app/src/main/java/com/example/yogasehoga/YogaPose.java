package com.example.yogasehoga;

import java.util.List;

public class YogaPose {
    private String yogaName, imageUrl, videoLink;
    private String step1, step2, step3;
    private String benefits, precautions;
    private List<String> ageGroup;
    private List<String> illness;

    public YogaPose() {
        // Empty constructor for Firebase
    }

    public YogaPose(String yogaName, String imageUrl, String videoLink, String step1, String step2, String step3,
                    String benefits, String precautions, List<String> ageGroup, List<String> illness) {
        this.yogaName = yogaName;
        this.imageUrl = imageUrl;
        this.videoLink = videoLink;
        this.step1 = step1;
        this.step2 = step2;
        this.step3 = step3;
        this.benefits = benefits;
        this.precautions = precautions;
        this.ageGroup = ageGroup;
        this.illness = illness;
    }

    public String getYogaName() { return yogaName; }
    public String getImageUrl() { return imageUrl; }
    public String getVideoLink() { return videoLink; }
    public String getStep1() { return step1; }
    public String getStep2() { return step2; }
    public String getStep3() { return step3; }
    public String getBenefits() { return benefits; }
    public String getPrecautions() { return precautions; }
    public List<String> getAgeGroup() { return ageGroup; }
    public List<String> getIllness() { return illness; }
}