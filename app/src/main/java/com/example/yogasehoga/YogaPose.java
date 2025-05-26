package com.example.yogasehoga;

import java.util.List;

public class YogaPose {
    public String YogaName;
    public String YogaImg;
    public String VideoLink;
    public String Step1;
    public String Step2;
    public String Step3;
    public String Benifits;
    public String Caution;
    public List<String> AgeGroup;
    public List<String> IllNes;
    public YogaPose() {}

    public YogaPose(String yogaName, String yogaImg, String videoLink,
                    String step1, String step2, String step3,
                    String benifits, String caution, List<String> ageGroup, List<String> illNes) {
        YogaName = yogaName;
        YogaImg = yogaImg;
        VideoLink = videoLink;
        Step1 = step1;
        Step2 = step2;
        Step3 = step3;
        Benifits = benifits;
        Caution = caution;
        AgeGroup = ageGroup;
        IllNes = illNes;
    }
}
