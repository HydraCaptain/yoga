package com.example.yogasehoga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Home extends Fragment {
    Button yogarecombtn, painrecombtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yogarecombtn = view.findViewById(R.id.viewmore);
        painrecombtn = view.findViewById(R.id.viewmore2);

        yogarecombtn.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Yogarecommendation())
                    .addToBackStack(null)
                    .commit();
        });

        painrecombtn.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Painarea())
                    .addToBackStack(null)
                    .commit();
        });
    }
}