package com.example.yogasehoga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.yogasehoga.All_Yoga_Info.YogaAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    ImageButton yogarecombtn, allyogarecom;
    ImageButton armbtn, anklebtn, backbtn, balancebtn, chestbtn, hipsbtn, kneebtn, relaxbtn;
    SearchView searchView;

    private RecyclerView recyclerView;
    private List<String> yogaList;
    private List<String> filteredList;
    private YogaAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allyogarecom = view.findViewById(R.id.allImageButton);  //this is yoga recommendation button on top of home page
        yogarecombtn = view.findViewById(R.id.recommendedyogabtn);    //this is all yoga pose button on bottom of home page

        //these all are specific pain buttons on home page
        armbtn = view.findViewById(R.id.armre);
        anklebtn = view.findViewById(R.id.anklere);
        backbtn = view.findViewById(R.id.backre);
        balancebtn = view.findViewById(R.id.balancere);
        chestbtn = view.findViewById(R.id.chestre);
        hipsbtn = view.findViewById(R.id.hipsre);
        kneebtn = view.findViewById(R.id.kneere);
        relaxbtn = view.findViewById(R.id.relaxre);


        searchView = view.findViewById(R.id.mysearch);
        searchView.setFocusable(true);
        searchView.setFocusableInTouchMode(true);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference yogaCollection = db.collection("YogaPose");

// Optional: Customize hint text
        searchView.setQueryHint("Search yoga...");

        recyclerView = view.findViewById(R.id.yogaSuggestionsRV);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample Yoga List
//        yogaList = Arrays.asList("Downward Dog", "Cobra", "Mountain Pose", "Tree Pose", "Bridge Pose");
//        filteredList = new ArrayList<>(yogaList);

        yogaList = new ArrayList<>();
        filteredList = new ArrayList<>();

        adapter = new YogaAdapter(filteredList, getContext(), new YogaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String yogaName) {
                // Open new Activity with yogaName
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




        //Fetch data from Firebase
        FirebaseFirestore.getInstance().collection("YogaPose")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    yogaList.clear();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String name = document.getString("yogaName");
                        if (name != null) {
                            yogaList.add(name);
                            Log.d("YogaSize", String.valueOf(yogaList.size()));
                        }
                    }
                    filteredList.clear();
//                    filteredList.addAll(yogaList);
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to fetch yoga poses", Toast.LENGTH_SHORT).show());



        // SearchView logic
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });


        yogarecombtn.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Yogarecommendation())
                    .addToBackStack(null)
                    .commit();
        });

        armbtn.setOnClickListener(v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new ArmPage())
                            .addToBackStack(null)
                            .commit();
        });

        anklebtn.setOnClickListener(v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new Ankle())
                            .addToBackStack(null)
                            .commit();
        });

        backbtn.setOnClickListener(v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new Back())
                            .addToBackStack(null)
                            .commit();
        });

        balancebtn.setOnClickListener(v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new Balance())
                            .addToBackStack(null)
                            .commit();
        });

        chestbtn.setOnClickListener(v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new Chest())
                            .addToBackStack(null)
                            .commit();
        });

        hipsbtn.setOnClickListener(v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new Hips())
                            .addToBackStack(null)
                            .commit();
        });

        kneebtn.setOnClickListener(v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new Knee())
                            .addToBackStack(null)
                            .commit();
        });

        relaxbtn.setOnClickListener(v -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, new Relax())
                            .addToBackStack(null)
                            .commit();
        });
        allyogarecom.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new YogaListFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void filter(String text) {
        filteredList.clear();
        for (String name : yogaList) {
            if (name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(name);
            }
        }
        adapter.notifyDataSetChanged();
    }
}