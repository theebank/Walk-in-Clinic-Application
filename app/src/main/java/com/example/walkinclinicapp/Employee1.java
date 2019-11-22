package com.example.walkinclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.List;

public class Employee1 extends AppCompatActivity {

    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee1);
        i = getIntent();
        String x =i.getStringExtra("hide");
        final Button updatebtn = findViewById(R.id.UpdateBtn);
        final Button serv = findViewById(R.id.ServiceBtn);
        final Button clinic = findViewById(R.id.Clinichrs);
        final Button manage = findViewById(R.id.Managehrs);


        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(i.getStringExtra("Username")).exists()){
                    Employee username = dataSnapshot.child(i.getStringExtra("Username")).getValue(Employee.class);
                    if(username.getAddress() != null){
                    updatebtn.setVisibility(View.INVISIBLE);}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(x.equals("1")){
           updatebtn.setVisibility(View.INVISIBLE);
        }

    }

    public void accountupdatebtn(View view){

        Intent intent = new Intent(getApplicationContext(), Employee2account.class);
        intent.putExtra("Username",i.getStringExtra("Username"));
        startActivityForResult (intent,0);
    }
    public void servicebtn(View view){
        Intent intent = new Intent(getApplicationContext(),Employee3Service.class);
        intent.putExtra("Username",i.getStringExtra("Username"));
        startActivityForResult(intent,0);
    }
    public void clinichrs(View view){
        Intent intent = new Intent(getApplicationContext(),Employee4Clinic.class);
        intent.putExtra("Username",i.getStringExtra("Username"));
        startActivityForResult(intent,0);
    }
    public void managehrs(View view){
        Intent intent = new Intent(getApplicationContext(),Employee5Hours.class);
        intent.putExtra("Username",i.getStringExtra("Username"));
        startActivityForResult(intent,0);
    }
}
