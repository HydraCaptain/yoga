package com.example.yogasehoga;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import android.util.Log;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Yogarecommendation extends Fragment {

    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    private String safeEmail;
    private String userIllness;
    private RecyclerView recyclerView;
    private YogaPoseAdapter adapter;
    private List<YogaPose> yogaPoseList;
    private ProgressBar progressBar;
    private ExecutorService executorService;
    private Handler mainHandler;

    public Yogarecommendation() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yogarecommendation, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerYoga);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        yogaPoseList = new ArrayList<>();
        adapter = new YogaPoseAdapter(getContext(), yogaPoseList, new YogaPoseAdapter.OnYogaPoseClickListener() {
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

        // Initialize ProgressBar
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        // Initialize Executor and Handler
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());

        // Fetch user illness and yoga poses
//        initAndFetchUserIllness();

        //this is temp from here
        db = FirebaseFirestore.getInstance();

        db.collection("YogaPose")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    yogaPoseList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        YogaPose pose = doc.toObject(YogaPose.class);
                        Log.d("YogaListFragment", "Name: " + pose.getYogaName() + ", Image: " + pose.getImageUrl());
                        yogaPoseList.add(pose);
                    }
                    Log.d("YogaListFragment", "Total poses: " + yogaPoseList.size());
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    // Handle error
                });
        //here
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Shutdown executor to prevent memory leaks
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    private void initAndFetchUserIllness() {
        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            safeEmail = convertToSafeEmail(currentUser.getEmail());
            fetchUserIllness();
        } else {
            mainHandler.post(() -> Toast.makeText(getContext(), "User not logged in", Toast.LENGTH_SHORT).show());
        }
    }

    private String convertToSafeEmail(String email) {
        return email.replace(".", "_").replace("@", "_");
    }

    private void fetchUserIllness() {
        mainHandler.post(() -> progressBar.setVisibility(View.VISIBLE)); // Show loading
        db.collection("Users")
                .document(safeEmail)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    mainHandler.post(() -> progressBar.setVisibility(View.GONE)); // Hide loading
                    if (documentSnapshot.exists()) {
                        userIllness = documentSnapshot.getString("discomfort");

                        if (userIllness != null && !userIllness.isEmpty()) {
                            // Trim "discomfort:" if present (likely not in Firestore data)
                            String cleanedIllness = userIllness.replace("discomfort:", "").trim();
                            mainHandler.post(() -> {
                                Toast.makeText(getContext(), "Discomfort: " + cleanedIllness, Toast.LENGTH_SHORT).show();
                                Log.d("Yogarecommendation", "Discomfort: " + cleanedIllness);
                            });
                            // Fetch yoga poses in a separate thread
                            executorService.execute(() -> fetchYogaPoses(cleanedIllness));
                        } else {
                            mainHandler.post(() -> Toast.makeText(getContext(), "No illness data found", Toast.LENGTH_SHORT).show());
                        }
                    } else {
                        mainHandler.post(() -> Toast.makeText(getContext(), "User document not found", Toast.LENGTH_SHORT).show());
                    }
                })
                .addOnFailureListener(e -> {
                    mainHandler.post(() -> {
                        progressBar.setVisibility(View.GONE); // Hide loading
                        Toast.makeText(getContext(), "Failed to fetch illness", Toast.LENGTH_SHORT).show();
                    });
                    Log.e("Yogarecommendation", "Error fetching illness: ", e);
                });
    }

    private void fetchYogaPoses(String illness) {
        mainHandler.post(() -> progressBar.setVisibility(View.VISIBLE)); // Show loading
        db.collection("YogaPose")
                .whereEqualTo("illness", illness)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<YogaPose> tempList = new ArrayList<>();
                    if (!queryDocumentSnapshots.isEmpty()) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            YogaPose yogaPose = document.toObject(YogaPose.class);
                            tempList.add(yogaPose);
                        }
                    }
                    // Update UI on main thread
                    mainHandler.post(() -> {
                        progressBar.setVisibility(View.GONE); // Hide loading
                        yogaPoseList.clear();
                        yogaPoseList.addAll(tempList);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Found " + yogaPoseList.size() + " yoga poses for " + illness, Toast.LENGTH_SHORT).show();
                    });
                })
                .addOnFailureListener(e -> {
                    mainHandler.post(() -> {
                        progressBar.setVisibility(View.GONE); // Hide loading
                        Toast.makeText(getContext(), "Failed to fetch yoga poses", Toast.LENGTH_SHORT).show();
                    });
                    Log.e("Yogarecommendation", "Error fetching yoga poses: ", e);
                });
    }
}



//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState){
//        super.onViewCreated(view, savedInstanceState);
//
//        linearLayout1 = view.findViewById(R.id.adhor);
//        linearLayout2 = view.findViewById(R.id.anandr);
//
//        linearLayout1.setOnClickListener(v->{
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.container, new AdhoMukhaSvanasana());
//            transaction.addToBackStack(null)
//                    .commit();
//        });
//
//        linearLayout2.setOnClickListener(v -> {
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.container, new AnandaBalasana());
//            transaction.addToBackStack(null)
//                    .commit();
//        });
//
//
//
//    }
