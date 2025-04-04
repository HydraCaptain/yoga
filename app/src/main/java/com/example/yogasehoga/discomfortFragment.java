package com.example.yogasehoga;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class discomfortFragment extends Fragment {
    Button button;
    ProgressBar progressBar;
    String name, gender, mobile, email;
    String motivation;
    String maingoal;
    String age, height, weight, targetwt;
    String discomfort;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discomfort, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.pbarr);

        List<LinearLayout> linearLayouts = Arrays.asList(
                view.findViewById(R.id.lnone),
                view.findViewById(R.id.lwrist),
                view.findViewById(R.id.larm),
                view.findViewById(R.id.lback),
                view.findViewById(R.id.lkneee),
                view.findViewById(R.id.lankle)
        );
        List<RadioButton> radioButtons = Arrays.asList(
                view.findViewById(R.id.none),
                view.findViewById(R.id.wrist),
                view.findViewById(R.id.arm),
                view.findViewById(R.id.back),
                view.findViewById(R.id.kneee),
                view.findViewById(R.id.ankle)
        );

        Lcilck.setupLinearLayoutClick(linearLayouts, radioButtons);

        Bundle bundle3 = getArguments();
            name = bundle3.getString("name");
            gender = bundle3.getString("gender");
            mobile = bundle3.getString("mobile");
            email = bundle3.getString("email", "").replace(".", "_");
            motivation = bundle3.getString("motivation");
            maingoal =bundle3.getString("maingoal");
            age = bundle3.getString("age");
            height = bundle3.getString("height");
            weight = bundle3.getString("weight");
            targetwt =bundle3.getString("targetweight");



        button = view.findViewById(R.id.button5);

        button = view.findViewById(R.id.button5);

        // Handle button click to send data to Firebase
        button.setOnClickListener(v -> {
            // Get selected discomfort value
            discomfort = null;
            for (RadioButton radioButton : radioButtons) {
                if (radioButton.isChecked()) {
                    discomfort = radioButton.getTag().toString();
                    break;
                }
            }

            if (discomfort == null) {
                Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }
            // Send data to Firebase
            progressBar.setVisibility(View.VISIBLE);
            sendDataToFirebase();
        });
    }

// this is to save data in firebase

    private void sendDataToFirebase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String safeEmail = email.replace(".", "_").replace("@", "_");
        // Create a user data object
        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("gender", gender);
        userData.put("mobile", mobile);
        userData.put("email", email);
        userData.put("motivation", motivation);
        userData.put("maingoal", maingoal);
        userData.put("age", age);
        userData.put("height", height);
        userData.put("weight", weight);
        userData.put("targetweight", targetwt);
        userData.put("discomfort", discomfort);

        // Store data in Firestore using Email id as the document ID
        db.collection("Users").document(safeEmail)
                .set(userData)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(requireContext(), "Data saved successfully!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(requireActivity(), MainActivity.class));
                    requireActivity().finish();

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(requireContext(), "Failed to save data", Toast.LENGTH_SHORT).show();
                });
    }



}