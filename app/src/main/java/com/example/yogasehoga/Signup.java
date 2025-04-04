package com.example.yogasehoga;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.Manifest;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class Signup extends AppCompatActivity {
    // 1st linearlayout elements
    EditText editText11, editText12, editText13, editText14, editText15;
    ProgressBar progressBar;
    Button button11;
    LinearLayout linearLayout1, linearLayout2;
    RadioButton radioButton1, radioButton2;
    TextView textView11;
    String phno;
    int otp=0;
    FirebaseAuth mAuth;
    String gender = "";
    // 2nd linearlayout elements
    EditText editText21, editText22, editText23, editText24, editText25, editText26;
    Button button21;
    TextView textView21, textView22, textView23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainsignup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //From here linearlyout-1st (firstfil) for user detail fill

        linearLayout1 = findViewById(R.id.firstfil);
        linearLayout2 = findViewById(R.id.secondfil);
        editText11 = findViewById(R.id.name);
        editText12 = findViewById(R.id.mobno);
        editText13 = findViewById(R.id.pass);
        editText14 = findViewById(R.id.email);
        editText15 = findViewById(R.id.conpass);
        progressBar = findViewById(R.id.pbar);
        button11 = findViewById(R.id.subbtn);
        radioButton1 = findViewById(R.id.male);
        radioButton2 = findViewById(R.id.female);
        textView11 = findViewById(R.id.lobutton);
        mAuth = FirebaseAuth.getInstance();



        //Already have an account button
        textView11.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this, login.class);
            startActivity(intent);
            finish();
        });

        //Button click listener
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText11.getText().toString();
                String mobile = editText12.getText().toString();
                String password = editText13.getText().toString();
                String email = editText14.getText().toString();
                String conpass = editText15.getText().toString();
                phno = editText12.getText().toString();

                Log.e("PH",phno);

                if (name.isEmpty()){
                    Toast.makeText(Signup.this, "Please enter a valid name", Toast.LENGTH_SHORT).show();
                } else if (radioButton1.isChecked() == false && radioButton2.isChecked() == false) {
                    Toast.makeText(Signup.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                } else if (mobile.isEmpty() || !mobile.matches("^[6-9]\\d{9}$")) {
                    Toast.makeText(Signup.this, "Please enter a valid 10-digit ", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()    ){
                    Toast.makeText(Signup.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty() || password.length() < 6) {
                        Toast.makeText(Signup.this, "Please enter minimum 6-digit password", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(conpass) ) {
                    Toast.makeText(Signup.this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
                } else if (ContextCompat.checkSelfPermission(Signup.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Signup.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                } else {
                    sendOTP();
                    progressBar.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    Toast.makeText(Signup.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });




        //From here linearlayout 2nd(2ndfil) for opt verification

        editText21 =findViewById(R.id.otpfield1);
        editText22 =findViewById(R.id.otpfield2);
        editText23 = findViewById(R.id.otpfield3);
        editText24 = findViewById(R.id.otpfield4);
        editText25 = findViewById(R.id.otpfield5);
        editText26 = findViewById(R.id.otpfield6);

        textView21 = findViewById(R.id.mnotex);
        textView22 = findViewById(R.id.mno);
        textView23 = findViewById(R.id.textresendotp);
        button21 = findViewById(R.id.button2);


        String mobile = editText12.getText().toString();
        textView22.setText(mobile);



        button21.setOnClickListener(new View.OnClickListener() {

            //OTP verification
            @Override
            public void onClick(View v) {
                String otpcode = editText21.getText().toString().trim() +
                        editText22.getText().toString().trim() +
                        editText23.getText().toString().trim() +
                        editText24.getText().toString().trim() +
                        editText25.getText().toString().trim() +
                        editText26.getText().toString().trim();

                if (otpcode.isEmpty() || otpcode.length() < 6 || !otpcode.equals(String.valueOf(otp))) {
                    Toast.makeText(Signup.this, "Please enter a valid OTP", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(Signup.this, "OTP verified successfully", Toast.LENGTH_SHORT).show();
                    signup();
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(Signup.this, fragholdersign.class);
                    intent.putExtra("name", editText11.getText().toString());
                    intent.putExtra("gender", radioButton1.isChecked() ? "Male" : "Female");
                    intent.putExtra("mobile", editText12.getText().toString());
                    intent.putExtra("email", editText14.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });

        otpmove();

    }

    //OTP filler move from left to right using loop
    private void otpmove() {
        EditText[] editTexts = new EditText[]{editText21, editText22, editText23, editText24, editText25, editText26};
        for (int i = 0; i < editTexts.length - 1; i++) { // Prevent going out of bounds
            int nextIndex = i + 1;
            editTexts[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!s.toString().trim().isEmpty()) {
                        editTexts[nextIndex].requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendOTP();

            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String generateOTP() {
        otp = (int) (Math.random() * 900000) + 100000; // 6-digit OTP
        return String.valueOf(otp);
    }


    private void sendOTP() {

        String otpCode = generateOTP(); // Get a dynamic OTP
        String message = ": Is your OTP for verification  ";
        phno = editText12.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage(otpCode + " " + message);
        String phoneNumber = phno;
        smsManager.sendMultipartTextMessage(phoneNumber,null,parts,null,null);

    }
/// this is for firebase authentication

    public void signup() {
        String password = editText13.getText().toString();
        String email = editText14.getText().toString();
        mAuth.createUserWithEmailAndPassword( email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Signup.this, "Account is created", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Signup.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
