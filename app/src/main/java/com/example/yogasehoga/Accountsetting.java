package com.example.yogasehoga;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Accountsetting extends Fragment {
Button userAge, userCurrentWeight, userTargetWeight, userHeight, userGender;
FirebaseAuth mAuth;
FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accountsetting, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userAge = view.findViewById(R.id.userAge);
        userCurrentWeight = view.findViewById(R.id.userCurrentWeight);
        userTargetWeight = view.findViewById(R.id.userTargetWeight);
        userHeight = view.findViewById(R.id.userHeight);
        userGender = view.findViewById(R.id.userGender);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            String safeEmail = email.replace(".", "_").replace("@", "_");
            Log.d("FirestoreDebug", "Trying to fetch document for email: " + email);

            db.collection("Users").document(safeEmail)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            userAge.setText("Age: " + documentSnapshot.getString("age"));
                            userCurrentWeight.setText("Weight: " + documentSnapshot.getString("weight"));
                            userTargetWeight.setText("Target: " + documentSnapshot.getString("targetweight"));
                            userHeight.setText("Height: " + documentSnapshot.getString("height"));
                            userGender.setText("Gender: " + documentSnapshot.getString("gender"));
                            Log.d("userAge", userAge.getText().toString());
                            Log.d("userCurrentWeight", userCurrentWeight.getText().toString());
                            Log.d("userTargetWeight", userTargetWeight.getText().toString());
                            Log.d("userHeight", userHeight.getText().toString());
                            Log.d("userGender", userGender.getText().toString());
                        } else {
                            Toast.makeText(requireContext(), "No user data found", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT).show();
                    });
        }


        userAge.setOnClickListener(v -> showUpdateDialog("age", "Enter your age", userAge));
        userCurrentWeight.setOnClickListener(v -> showUpdateDialog("weight", "Enter your current weight", userCurrentWeight));
        userTargetWeight.setOnClickListener(v -> showUpdateDialog("targetweight", "Enter your target weight", userTargetWeight));
        userHeight.setOnClickListener(v -> showUpdateDialog("height", "Enter your height", userHeight));
        userGender.setOnClickListener(v -> showUpdateDialog("gender", "Enter your gender", userGender));

    }





    //updated data in firebase
    private void showUpdateDialog(String fieldKey, String hint, Button button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Update " + fieldKey);

        final EditText input = new EditText(requireContext());
        input.setHint(hint);
        builder.setView(input);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String newValue = input.getText().toString().trim();
            if (!newValue.isEmpty()) {
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String safeEmail = email.replace(".", "_").replace("@", "_");

                FirebaseFirestore.getInstance()
                        .collection("Users")
                        .document(safeEmail)
                        .update(fieldKey, newValue)
                        .addOnSuccessListener(aVoid -> {
                            button.setText((fieldKey) + ": " + newValue);
                            Toast.makeText(requireContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(requireContext(), "Update failed", Toast.LENGTH_SHORT).show();
                        });
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

}