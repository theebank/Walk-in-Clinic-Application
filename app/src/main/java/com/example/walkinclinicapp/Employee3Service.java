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

public class Employee3Service extends AppCompatActivity {
    Spinner all,offered;
    //Firebase
    FirebaseDatabase database;
    DatabaseReference refservices,refusers;
    Query query;
    List<String> titleListuser = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee3_service);


        final Intent i = getIntent();

        all = findViewById(R.id.allservices);
        offered = findViewById(R.id.servicesyouoffer);

        database = FirebaseDatabase.getInstance();
        refservices = database.getReference("Services");
        refusers = database.getReference("Users").child(i.getStringExtra("Username"));
        //Adding services to the all services spinner
        if(titleList.isEmpty()){
        query = refservices.orderByChild("type");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                titleList.add("--Choose a Service--");
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String titlename = dataSnapshot1.child("type").getValue(String.class);
                    titleList.add(titlename);
                }
                all.setAdapter(null);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee3Service.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                all.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Employee3Service.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });}
        //Adding services to your service spinner

        if(titleListuser.isEmpty()){
            query = refusers.child("services").orderByValue();
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    titleListuser.clear();
                    titleListuser.add("--Choose a Service--");
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            String titlename = dataSnapshot1.getValue(String.class);
                            titleListuser.add(titlename);


                    }
                    //Changing the value in the spinner
                    offered.setAdapter(null);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee3Service.this, android.R.layout.simple_spinner_item, titleListuser);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    offered.setAdapter(arrayAdapter);
                  }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(Employee3Service.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }

    }
    public void addservicebtn(View view){
        String allspinner = all.getSelectedItem().toString();
        if(!(titleListuser.contains(allspinner))) {
            titleListuser.add(allspinner);
        }
        titleListuser.remove(0);
        int i = 0;
        for(String x: titleListuser){

            refusers.child("services").child(""+i).setValue(x);
            i++;
        }



        //Changing the values in the spinner
        offered.setAdapter(null);
        query = refusers.child("services").orderByValue();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                titleListuser.clear();
                titleListuser.add("--Choose a Service--");
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String titlename = dataSnapshot1.getValue(String.class);
                    titleListuser.add(titlename);


                }

                //Changing the value in the spinner
                offered.setAdapter(null);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee3Service.this, android.R.layout.simple_spinner_item, titleListuser);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                offered.setAdapter(arrayAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Employee3Service.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }
    public void deletebtn(View view){

        refusers.child("services").child(String.valueOf(offered.getSelectedItemPosition()-1)).removeValue();
        titleListuser.remove(offered.getSelectedItem());
        titleListuser.remove(0);
        int i = 0;
        for(String x: titleListuser){

            refusers.child("services").child(""+i).setValue(x);
            i++;
        }


        //Changing the values in the spinner
        offered.setAdapter(null);
        query = refusers.child("services").orderByValue();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                titleListuser.clear();
                titleListuser.add("--Choose a Service--");
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String titlename = dataSnapshot1.getValue(String.class);
                    titleListuser.add(titlename);


                }

                //Changing the value in the spinner
                offered.setAdapter(null);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee3Service.this, android.R.layout.simple_spinner_item, titleListuser);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                offered.setAdapter(arrayAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Employee3Service.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
