package com.example.yogasehoga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class bodydata extends Fragment {
 EditText editText1, editText2, editText3, editText4;
 Button button;
 String age, height, weight, targetweight;
 String name, gender, mobile, email, motivation, maingoal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bodydata, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle2 = getArguments();
            name = bundle2.getString("name");
            gender =bundle2.getString("gender");
            mobile =bundle2.getString("mobile");
            email = bundle2.getString("email");
            motivation = bundle2.getString("motivation");
            maingoal =bundle2.getString("maingoal");
            Log.d("bodydata", "Name: " + name+"gender: "+gender+"mobile: "+mobile+"email: "+email+"motivation: "+motivation+"maingoal: "+maingoal);

        editText1 = view.findViewById(R.id.agee);
        editText2 = view.findViewById(R.id.heightt);
        editText3 = view.findViewById(R.id.weightt);
        editText4 = view.findViewById(R.id.targetw);
        button = view.findViewById(R.id.button6);

        button.setOnClickListener(v -> {

             age = editText1.getText().toString();
             height = editText2.getText().toString();
             weight = editText3.getText().toString();
             targetweight = editText4.getText().toString();

            if (age.isEmpty() || !isValidAge(age)) {
                Toast.makeText(requireContext(), "Please enter a valid age", Toast.LENGTH_SHORT).show();
            } else if (height.isEmpty() || !isValidHeight(height)) {
                Toast.makeText(requireContext(), "Please enter a valid height", Toast.LENGTH_SHORT).show();
            } else if (weight.isEmpty() || !isValidWeight(weight)) {
                Toast.makeText(requireContext(), "Please enter a valid weight", Toast.LENGTH_SHORT).show();
            } else if (targetweight.isEmpty() || !isValidTargetWeight(targetweight)) {
                Toast.makeText(requireContext(), "Please enter a valid target weight", Toast.LENGTH_SHORT).show();
            } else {
                Bundle bundle3 = new Bundle();

                bundle3.putString("name", name);
                bundle3.putString("gender", gender);
                bundle3.putString("mobile", mobile);
                bundle3.putString("email", email);
                bundle3.putString("motivation", motivation);
                bundle3.putString("maingoal", maingoal);
                bundle3.putString("age", age);
                bundle3.putString("height", height);
                bundle3.putString("weight", weight);
                bundle3.putString("targetweight", targetweight);

                discomfortFragment nextFragment2 = new discomfortFragment();
                nextFragment2.setArguments(bundle3);
//                ((fragholdersign) requireActivity()).replaceFragment(nextFragment2);

                assert getFragmentManager() != null;
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainhold, nextFragment2)
                        .addToBackStack(null)
                        .commit();
            }


        });

    }
    public boolean isValidAge(String ageText) {
        int age = Integer.parseInt(ageText);
        return age >= 5 && age <= 80;
    }
    public boolean isValidHeight(String heightText) {
        int height = Integer.parseInt(heightText);
        return height >= 50 && height <= 250;
    }
    public boolean isValidWeight(String weightText) {
        int weight = Integer.parseInt(weightText);
        return weight >= 30 && weight <= 150;
    }
    public boolean isValidTargetWeight(String targetWeightText) {
        int targetWeight = Integer.parseInt(targetWeightText);
        return targetWeight >= 30 && targetWeight <= 80;
    }

}