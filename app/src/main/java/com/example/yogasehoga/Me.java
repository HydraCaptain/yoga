package com.example.yogasehoga;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Me extends Fragment {

    Button Accountbtn, logoutbtn;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Accountbtn = view.findViewById(R.id.btnAccount);
        logoutbtn = view.findViewById(R.id.logout);

        logoutbtn.setOnClickListener(v -> {
            new AlertDialog.Builder(requireContext())
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        mAuth.signOut();
                        startActivity(new Intent(requireActivity(), login.class));
                        requireActivity().finish();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss(); // just close the dialog
                    })
                    .show();
        });

        Accountbtn.setOnClickListener(v->{
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, new Accountsetting());
            transaction.addToBackStack(null)
                    .commit();
        });


    }

}