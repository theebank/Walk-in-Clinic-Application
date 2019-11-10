package com.example.walkinclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class Admin1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);


        TextView AdminWelcometxt = findViewById(R.id.AdminWelcometxt);
        AdminWelcometxt.setText("Welcome to the Admin Homepage.");

    }
    public void onLogoutbtn(View view){
        Toast.makeText(Admin1.this, "Successful Logout!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult (intent,0);
    }
    public void useradminbtn(View view){
            Intent intent = new Intent(getApplicationContext(), Admin2User.class);
            startActivityForResult (intent,0);
    }
    public void clinicadminbtn(View view){
            Intent intent = new Intent(getApplicationContext(),Admin3Clinic.class);
            startActivityForResult(intent,0);
    }
    public void serviceadminbtn(View view){
        Intent intent = new Intent(getApplicationContext(),Admin4Service.class);
        startActivityForResult(intent,0);

    }
}
