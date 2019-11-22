package com.example.walkinclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.*;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClinicCreation extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference clinics;

    EditText ClinicNameField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_creation);

        database = FirebaseDatabase.getInstance();
        clinics = database.getReference("Clinics");

        ClinicNameField = findViewById(R.id.ClinicNameField);

    }


    public void OnCreatetheClinicButton(View view) {
        final String[] empty = {"","","","","","",""};
        final List emptylist = new ArrayList<String>(Arrays.asList(empty));
        final Clinic user = new Clinic(ClinicNameField.getText().toString(),emptylist);
        clinics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user.getName()).exists()){
                    Toast.makeText(ClinicCreation.this, "The Clinic already exists.",Toast.LENGTH_SHORT).show();
                }else{
                    clinics.child(user.getName()).setValue(user);
                    Toast.makeText(ClinicCreation.this, "Successfully Created.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), Admin3Clinic.class);
        startActivityForResult (intent,0);
    }
}
