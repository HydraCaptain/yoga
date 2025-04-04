package com.example.yogasehoga;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class fragholdersign extends AppCompatActivity {
String name, gender, mobile, email;
String motivation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fragholdersign);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");
        mobile = intent.getStringExtra("mobile");
        email = intent.getStringExtra("email");
        motivation = intent.getStringExtra("motivation");



        if (savedInstanceState == null) {
            replaceFragment(new motivateFragment());
        }
    }

    public void replaceFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("gender", gender);
        bundle.putString("mobile", mobile);
        bundle.putString("email", email);
        bundle.putString("motivation", motivation);



        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainhold, fragment)
                .addToBackStack(null)
                .commit();
    }

}