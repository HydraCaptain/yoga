package com.example.yogasehoga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class YogaListFragment extends Fragment {

    RecyclerView recyclerView;
    YogaPoseAdapter adapter;
    List<YogaPose> yogaList = new ArrayList<>();
    FirebaseFirestore db;

    public YogaListFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yogalistfragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerYoga);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        db.collection("YogaPose")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    yogaList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        YogaPose pose = doc.toObject(YogaPose.class);
                        Log.d("YogaListFragment", "Name: " + pose.getYogaName() + ", Image: " + pose.getImageUrl());
                        yogaList.add(pose);
                    }
                    Log.d("YogaListFragment", "Total poses: " + yogaList.size());
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // Handle error
                });

        return view;
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState){
//        super.onViewCreated(view, savedInstanceState);
//
//        linearLayout1 = view.findViewById(R.id.adho);
//        linearLayout2 = view.findViewById(R.id.ardh);
//        linearLayout3 = view.findViewById(R.id.anand);
//
//        linearLayout1.setOnClickListener(v->{
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.container, new AdhoMukhaSvanasana());
//            transaction.addToBackStack(null)
//                    .commit();
//        });
//
//        linearLayout2.setOnClickListener(v->{
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.container, new ArdhaMatsyendrasana());
//            transaction.addToBackStack(null)
//                    .commit();
//        });
//        linearLayout3.setOnClickListener(v -> {
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.container, new AnandaBalasana());
//            transaction.addToBackStack(null)
//                    .commit();
//        });
//        adapter = new YogaPoseAdapter(getContext(), yogaList);
//        recyclerView.setAdapter(adapter);
//
//        db = FirebaseFirestore.getInstance();
//
//        db.collection("YogaPose")
//                .get()
//                .addOnSuccessListener(queryDocumentSnapshots -> {
//                    yogaList.clear();
//                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
//                        YogaPose pose = doc.toObject(YogaPose.class);
//                        yogaList.add(pose);
//                    }
//                    adapter.notifyDataSetChanged();
//                })
//                .addOnFailureListener(e -> {
//                    // handle error
//                });
//
//
//
//
//    }
//    private void loadYogaPosesFromFirebase() {
//
//        db.collection("YogaPose)")
//                .get()
//                .addOnSuccessListener(queryDocumentSnapshots -> {
//                    yogaList.clear();
//                    for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
//                        YogaposeM pose = document.toObject(YogaposeM.class);
//                        Log.d(TAG, document.getId() + " => " + document.getData());
//                        yogaList.add(pose);
//                    }
//                    adapter.notifyDataSetChanged(); // Refresh RecyclerView
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
//                });
//    }
}

//        db = FirebaseFirestore.getInstance();
//        yogaList = new ArrayList<>();
//        adapter = new YogaPoseAdapter(yogaList);
//        recyclerView.setAdapter(adapter);
//
//        db.collection("cities")
//                .document("YogaPoseName")
//                .collection("YogaName")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });