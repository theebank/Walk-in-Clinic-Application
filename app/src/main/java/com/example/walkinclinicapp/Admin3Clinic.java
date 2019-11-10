package com.example.walkinclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.List;

public class Admin3Clinic extends AppCompatActivity {
    Spinner cliniclist,servicelist;
    FirebaseDatabase database;
    DatabaseReference ref,newref;
    Query query,query1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin3_clinic);

        TextView admin2user = findViewById(R.id.admin3clinic);
        admin2user.setText("Welcome to the Clinic Administration Page.");

        cliniclist = findViewById(R.id.spinnerclinic);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Clinics");

        servicelist = findViewById(R.id.spinnerServices);


        query = ref.orderByChild("name");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                titleList.add("--Choose a Clinic--");
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String titlename = dataSnapshot1.child("name").getValue(String.class);
                    titleList.add(titlename);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Admin3Clinic.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cliniclist.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Admin3Clinic.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        /*query1 = newref;
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> services = new ArrayList<>();
                services.add("--Choose a service--");
                for(DataSnapshot dataSnapshot2: dataSnapshot.getChildren()){
                    String servicename = dataSnapshot2.child("service"+tempcounter).getValue(String.class);
                    services.add(servicename);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Admin3Clinic.this, android.R.layout.simple_spinner_item, services);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                servicelist.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    }


    public void returnbtn(View view){
        Intent intent = new Intent(getApplicationContext(), Admin1.class);
        startActivityForResult (intent,0);
    }

    public void gotocreateclinic(View view){
        Intent intent = new Intent(getApplicationContext(), ClinicCreation.class);
        startActivityForResult (intent,0);
    }
}
