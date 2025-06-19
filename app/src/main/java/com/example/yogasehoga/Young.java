package com.example.yogasehoga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class Young extends Fragment {

    RecyclerView recyclerViewYoung;
    YogaPoseAdapter adapter;
    List<YogaPose> yogaList = new ArrayList<>();
    FirebaseFirestore db;


    public Young() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_young, container, false);

        recyclerViewYoung = view.findViewById(R.id.recyclerYoungYoga);
        recyclerViewYoung.setLayoutManager(new LinearLayoutManager(getContext()));

        yogaList = new ArrayList<>();
        adapter = new YogaPoseAdapter(getContext(), yogaList, new YogaPoseAdapter.OnYogaPoseClickListener() {
            @Override
            public void onYogaPoseClick(String yogaName) {
                // Handle click event
                YogaDetailsFragment fragment = new YogaDetailsFragment();

                Bundle args = new Bundle();
                args.putString("yogaName", yogaName);
                fragment.setArguments(args);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)  // Make sure this is your Fragment container ID
                        .addToBackStack(null)
                        .commit();
            }
        });
        recyclerViewYoung.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        db.collection("YogaPose")
                .whereArrayContains("ageGroup", "28-45")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    yogaList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        YogaPose pose = doc.toObject(YogaPose.class);
                        yogaList.add(pose);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // Handle error
                });

        return view;
    }
}