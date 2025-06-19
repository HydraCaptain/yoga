package com.example.yogasehoga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Family extends Fragment {

    ImageButton child1,teenage1,young1,old1;

    public Family() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_family, container, false);

        child1 = view.findViewById(R.id.Childbtn);
        teenage1 = view.findViewById(R.id.Teenagebtn);
        young1 = view.findViewById(R.id.Youngbtn);
        old1 = view.findViewById(R.id.Oldbtn);

        child1.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Child())
                    .addToBackStack(null)
                    .commit();
        });
        teenage1.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new TeenAge())
                    .addToBackStack(null)
                    .commit();
        });

        young1.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Young())
                    .addToBackStack(null)
                    .commit();
        });

        old1.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Old())
                    .addToBackStack(null)
                    .commit();
        });
        return view;
    }
}