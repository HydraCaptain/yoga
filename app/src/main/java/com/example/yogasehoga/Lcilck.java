package com.example.yogasehoga;

import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.util.List;

public class Lcilck {
    public static void setupLinearLayoutClick(List<LinearLayout> linearLayouts, List<RadioButton> radioButtons) {
        for (int i = 0; i < linearLayouts.size(); i++) {
            int finalI = i;
            linearLayouts.get(i).setOnClickListener(v -> {
                for (RadioButton rb : radioButtons) {
                    rb.setChecked(false);
                }
                radioButtons.get(finalI).setChecked(true);
            });
        }
    }
}
