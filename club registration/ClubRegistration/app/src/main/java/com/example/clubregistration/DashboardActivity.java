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

import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity {
    EditText clubName, studentName;
    Button registerClubBtn, viewClubsBtn;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        clubName = findViewById(R.id.clubName);
        studentName = findViewById(R.id.studentName);
        registerClubBtn = findViewById(R.id.registerClubBtn);
        viewClubsBtn = findViewById(R.id.viewClubsBtn);

        dbRef = FirebaseDatabase.getInstance().getReference("ClubRegistrations");

        registerClubBtn.setOnClickListener(v -> {
            String club = clubName.getText().toString();
            String student = studentName.getText().toString();
            String id = dbRef.push().getKey();

            HashMap<String, String> data = new HashMap<>();
            data.put("club", club);
            data.put("student", student);

            dbRef.child(id).setValue(data).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Club Registered", Toast.LENGTH_SHORT).show();
                }
            });
        });

        viewClubsBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, ViewClubsActivity.class));
        });
    }
}
