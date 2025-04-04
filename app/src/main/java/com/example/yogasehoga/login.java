package com.example.yogasehoga;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText editText1, editText2;
    ProgressBar progressBar;
    Button button1;
    TextView textView1;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editText1 = findViewById(R.id.Emailadd);
        editText2 = findViewById(R.id.passwordd);
        progressBar = findViewById(R.id.progress);
        button1 = findViewById(R.id.button);
        textView1 = findViewById(R.id.custom_button);

        mAuth = FirebaseAuth.getInstance();

        //Don't have account button it moves user to signup page from login page
        textView1.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, Signup.class);
            startActivity(intent);
            finish();
        });


        //Login button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                progressBar.setVisibility(View.GONE);
            }
        });


    }
    private void loginUser() {
        String email = editText1.getText().toString().trim();
        String password = editText2.getText().toString().trim();

        // Validation checks
        if (TextUtils.isEmpty(email)) {
            editText1.setError("Enter your email");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editText2.setError("Enter your password");
            return;
        }
        if (password.length() < 6) {
            editText2.setError("Password must be at least 6 characters");
            return;
        }

        // Show progress bar while logging in
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(login.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}