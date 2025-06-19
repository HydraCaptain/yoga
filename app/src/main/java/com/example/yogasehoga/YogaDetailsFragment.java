package com.example.yogasehoga;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class YogaDetailsFragment extends Fragment {

    private TextView yogaNameTV, benefitsTV, precautionsTV, stepsTV, ageGroupTV, illnessTV;
    private ImageView yogaImage;
    private Button watchVideoBtn;


    public YogaDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yoga_details, container, false);



        yogaImage = view.findViewById(R.id.yogaImage);
        yogaNameTV = view.findViewById(R.id.yogaNameTV);
        benefitsTV = view.findViewById(R.id.benefitsTV);
        precautionsTV = view.findViewById(R.id.precautionsTV);
        stepsTV = view.findViewById(R.id.stepsTV);
        ageGroupTV = view.findViewById(R.id.ageGroupTV);
        illnessTV = view.findViewById(R.id.illnessTV);
        watchVideoBtn = view.findViewById(R.id.watchVideoBtn);


        if (getArguments() != null) {
            String yogaName = getArguments().getString("yogaName");
            yogaNameTV.setText(yogaName);
            fetchYogaDetails(yogaName);
        }


        return view;
    }


    private void fetchYogaDetails(String yogaName) {
        FirebaseFirestore.getInstance().collection("YogaPose")
                .document(yogaName)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        //Get values
                        String imageUrl = documentSnapshot.getString("imageUrl");
                        String benefits = documentSnapshot.getString("benefits");
                        String precautions = documentSnapshot.getString("precautions");
                        String step1 = documentSnapshot.getString("step1");
                        String step2 = documentSnapshot.getString("step2");
                        String step3 = documentSnapshot.getString("step3");
                        List<String> ageGroup = (List<String>) documentSnapshot.get("ageGroup");
                        List<String> illness = (List<String>) documentSnapshot.get("illness");
                        String videoLink = documentSnapshot.getString("videoLink");

                        Log.e("imgUrl",imageUrl);

                        // Display
                        Glide.with(requireContext()).load(imageUrl).into(yogaImage);
                        benefitsTV.setText(benefits);
                        precautionsTV.setText(precautions);
                        stepsTV.setText("- " + step1 + "\n- " + step2 + "\n- " + step3);
                        ageGroupTV.setText(ageGroup != null ? android.text.TextUtils.join(", ", ageGroup) : "-");
                        illnessTV.setText(illness != null ? android.text.TextUtils.join(", ", illness) : "-");

                        watchVideoBtn.setOnClickListener(v -> {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoLink));
                            startActivity(intent);
                        });

                    } else {
                        Toast.makeText(getContext(), "Pose not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();
                });
    }
}