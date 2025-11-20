package com.example.clubregistration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {
    EditText emailSignup, passwordSignup;
    Button signupBtn;
    FirebaseAuth authSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailSignup = findViewById(R.id.email);
        passwordSignup = findViewById(R.id.password);
        signupBtn = findViewById(R.id.signUpBtn);
        authSignUp = FirebaseAuth.getInstance();

        signupBtn.setOnClickListener(v -> {
            String email = emailSignup.getText().toString();
            String pass = passwordSignup.getText().toString();

            authSignUp.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {

                if (task.isSuccessful()) {
                    Toast.makeText(this, "successfuly created", Toast.LENGTH_SHORT).show();
                    finish();
                } else {

                    Toast.makeText(this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
