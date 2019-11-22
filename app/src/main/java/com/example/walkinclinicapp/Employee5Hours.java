package com.example.walkinclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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

public class Employee5Hours extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    Query query;
    EditText monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee5_hours);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");

        monday = findViewById(R.id.Monday);
        tuesday = findViewById(R.id.Tuesday);
        wednesday = findViewById(R.id.Wednesday);
        thursday = findViewById(R.id.Thursday);
        friday=findViewById(R.id.Friday);
        saturday = findViewById(R.id.Saturday);
        sunday = findViewById(R.id.Sunday);

        final Intent i = getIntent();
        ref=ref.child(i.getStringExtra("Username")).child("hours");


        query = ref;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                int i = 0;
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    if (i==0){
                        monday.setText(dataSnapshot1.getValue(String.class));
                    }else if(i == 1){
                        tuesday.setText(dataSnapshot1.getValue(String.class));
                    }else if(i==2){
                        wednesday.setText(dataSnapshot1.getValue(String.class));
                    }else if(i==3){
                        thursday.setText(dataSnapshot1.getValue(String.class));
                    }else if(i==4){
                        friday.setText(dataSnapshot1.getValue(String.class));
                    }else if(i==5){
                        saturday.setText(dataSnapshot1.getValue(String.class));
                    }else if(i==6){
                        sunday.setText(dataSnapshot1.getValue(String.class));
                    }
                    i++;
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Employee5Hours.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });}

    public void updatebtn(View view){
        ref.child("0").setValue(monday.getText().toString());
        ref.child("1").setValue(tuesday.getText().toString());
        ref.child("2").setValue(wednesday.getText().toString());
        ref.child("3").setValue(thursday.getText().toString());
        ref.child("4").setValue(friday.getText().toString());
        ref.child("5").setValue(saturday.getText().toString());
        ref.child("6").setValue(sunday.getText().toString());

        monday.setText("");
        tuesday.setText("");
        wednesday.setText("");
        thursday.setText("");
        friday.setText("");
        saturday.setText("");
        sunday.setText("");

    }
}

